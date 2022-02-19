package dk.sdu;

import java.util.List;
import java.util.stream.Collectors;

import dk.sdu.metamodel.Machine;
import dk.sdu.metamodel.State;
import dk.sdu.metamodel.Transition;

public class MachineInterpreter {

	private Machine currentMachine;
	private State currentState;

	public void run(Machine m) {
		currentMachine = m;

		currentState = currentMachine.getInitialState();
	}

	public State getCurrentState() {
		return this.currentState;
	}

	public void processEvent(String string) {
		List<Transition> transitions = currentState.getTransitions();
		transitions = transitions.stream()
				.filter(entry -> string.equals(entry.getEvent())).collect(Collectors.toList());

		for (Transition transition : transitions) {
			if (!processCondition(transition)) {
				continue;
			}

			currentState = transition.getTarget();
			processOperation(transition);
			return;
		}
	}

	private void processOperation(Transition transition) {
		if (!transition.hasOperation()) {
			return;
		}

		if (transition.hasSetOperation()) {
			this.currentMachine.getIntegers().put(
					transition.getOperationVariableName(),
					transition.getOperationVariableValue()
			);
		} else {
			int currentInteger = getInteger(transition.getOperationVariableName());

			this.currentMachine.getIntegers().put(
					transition.getOperationVariableName(),
					currentInteger + transition.getOperationVariableValue()
			);
		}
	}

	private boolean processCondition(Transition transition) {
		if (!transition.isConditional()) {
			return true;
		}

		String variableName = transition.getConditionVariableName();
		int variableValue = transition.getConditionComparedValue();

		if (transition.isConditionEqual()) {
			return getInteger(variableName) == variableValue;
		} else if (transition.isConditionGreaterThan()) {
			return getInteger(variableName) > variableValue;
		} else if (transition.isConditionLessThan()) {
			return getInteger(variableName) < variableValue;
		}

		return false;
	}

	public int getInteger(String string) {
		return this.currentMachine.getIntegers().get(string);
	}

}

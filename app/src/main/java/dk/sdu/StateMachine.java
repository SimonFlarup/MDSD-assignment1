package dk.sdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dk.sdu.metamodel.Machine;
import dk.sdu.metamodel.State;
import dk.sdu.metamodel.Transition;

public class StateMachine {

	private List<State> stateList = new ArrayList<>();
	private Map<String, Integer> integerList = new HashMap<>();
	private State initialState;
	private State currentState;
	private Map<Transition, String> preBuildTransitions = new HashMap<>();
	private Transition currentTransition;

	public Machine build() {
		for (Map.Entry<Transition, String> entry: preBuildTransitions.entrySet()) {
			String targetName = entry.getValue();
			State target = stateList.stream().filter(
					state -> targetName.equals(state.getName())
			).findFirst().orElseThrow();

			entry.getKey().setTarget(target);
		}

		return new Machine(stateList, initialState, integerList);
	}

	public StateMachine state(String string) {
		currentState = new State(string);
		stateList.add(currentState);
		return this;
	}

	public StateMachine initial() {
		initialState = currentState;
		return this;
	}

	public StateMachine when(String string) {
		currentTransition = new Transition(string);
		return this;
	}

	public StateMachine to(String string) {
		this.preBuildTransitions.put(currentTransition, string);
		currentState.addTransition(currentTransition);
		return this;
	}

	public StateMachine integer(String string) {
		integerList.put(string, 0);
		return this;
	}

	public StateMachine set(String string, int i) {
		this.currentTransition.setOperation(string, i);
		return this;
	}

	public StateMachine increment(String string) {
		this.currentTransition.incrementOperation(string);
		return this;
	}

	public StateMachine decrement(String string) {
		this.currentTransition.decrementOperation(string);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		this.currentTransition.setConditionalEqual(string, i);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		this.currentTransition.setConditionalGreaterThan(string, i);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		this.currentTransition.setConditionalLessThan(string, i);
		return this;
	}

}

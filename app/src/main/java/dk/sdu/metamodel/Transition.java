package dk.sdu.metamodel;

import java.util.Map;

public class Transition {

	private String event;
	private State target;

	private Map.Entry<String, Integer> operationVariable;

	private boolean hasSetOperation = false;
	private boolean hasIncrementOperation = false;
	private boolean hasDecrementOperation = false;

	private Map.Entry<String, Integer> conditionalVariable;

	private boolean isEqual = false;
	private boolean isGreaterThan = false;
	private boolean isLessThan = false;

	public Transition(String event) {
		this.event = event;
	}

	public String getEvent() {
		return this.event;
	}

	public void setTarget(State target) {
		this.target = target;
	}

	public State getTarget() {
		return this.target;
	}

	public boolean hasSetOperation() {
		return hasSetOperation;
	}

	public void setOperation(String string, int i) {
		hasSetOperation = true;
		this.operationVariable = Map.entry(string, i);
	}

	public boolean hasIncrementOperation() {
		return hasIncrementOperation;
	}

	public void incrementOperation(String string) {
		hasIncrementOperation = true;
		this.operationVariable = Map.entry(string, 1);
	}

	public boolean hasDecrementOperation() {
		return hasDecrementOperation;
	}

	public void decrementOperation(String string) {
		hasDecrementOperation = true;
		this.operationVariable = Map.entry(string, -1);
	}

	public String getOperationVariableName() {
		return this.operationVariable.getKey();
	}

	public int getOperationVariableValue() {
		return this.operationVariable.getValue();
	}

	public boolean hasOperation() {
		return hasSetOperation() || hasIncrementOperation() || hasDecrementOperation();
	}

	public boolean isConditional() {
		return isConditionEqual() || isConditionGreaterThan() || isConditionLessThan();
	}

	public String getConditionVariableName() {
		return conditionalVariable.getKey();
	}

	public Integer getConditionComparedValue() {
		return conditionalVariable.getValue();
	}

	public boolean isConditionEqual() {
		return isEqual;
	}

	public void setConditionalEqual(String string, int i) {
		this.isEqual = true;
		this.conditionalVariable = Map.entry(string, i);
	}

	public boolean isConditionGreaterThan() {
		return isGreaterThan;
	}

	public void setConditionalGreaterThan(String string, int i) {
		this.isGreaterThan = true;
		this.conditionalVariable = Map.entry(string, i);
	}

	public boolean isConditionLessThan() {
		return isLessThan;
	}

	public void setConditionalLessThan(String string, int i) {
		this.isLessThan = true;
		this.conditionalVariable = Map.entry(string, i);
	}

}

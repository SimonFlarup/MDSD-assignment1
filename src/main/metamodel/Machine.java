package main.metamodel;

import java.util.List;
import java.util.Map;

public class Machine {

	private List<State> states;
	private Map<String, Integer> integers;
	private State initialState;

	public Machine(List<State> states, State initialState, Map<String, Integer> integers) {
		this.states = states;
		this.initialState = initialState;
		this.integers = integers;
	}

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		return states.stream().filter(state -> string.equals(state.getName())).findFirst().get();
	}

	public int numberOfIntegers() {
		return integers.size();
	}

	public boolean hasInteger(String string) {
		return integers.containsKey(string);
	}

	public Map<String, Integer> getIntegers() {
		return this.integers;
	}

}

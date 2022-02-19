package dk.sdu.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {

	private String name;
	private List<Transition> transitions = new ArrayList<>();

	public State(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public Transition getTransitionByEvent(String string) {
		return transitions.stream().filter(
				transition -> string.equals(transition.getEvent())
		).findFirst().orElseThrow();
	}

	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

}

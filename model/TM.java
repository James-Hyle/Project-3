package model;

import java.util.*;

public class TM {

    private LinkedHashSet<TMState> states;
    private Set<Character> alphabet;

    public TM(int numStates, int alphabetSize) {
        this.states = new LinkedHashSet<>(numStates);
        this.alphabet = new LinkedHashSet<>(alphabetSize);
    }

    public boolean addState(TMState state) {
        // check states to prevent duplicate before creating and adding new state
        if (!states.contains(getState(state.getName()))) {
            return states.add(state);
        }
        return false;
    }

    public boolean setAccept(String name) {
        TMState temp = getState(name);
        // check for valid state
        if (states.contains(temp)) {
            temp.setAcceptState();
            return true;
        }
        return false;
    }

    public void addSigma(char symbol) {
        if (symbol != 'e') {
            alphabet.add(symbol);
        }
    }

    public Set<Character> getSigma() {
        return alphabet;
    }

    public boolean addTransition(String fromState, char onSymb, String toState, String writeSymbol, String moveDirection) {
        TMState from = getState(fromState);
        // if from state isn't in system stop and return false
        if (!states.contains(from)) {
            return false;
        }
        TMTransition transition = new TMTransition(toState, writeSymbol, moveDirection);

        // if symbol is not in system alphabet or any states to or from are empty, stop and return false,
        // otherwise add transitions for state
        if (from != null && (alphabet.contains(onSymb) || onSymb == '0')) {
            from.addTransitions(onSymb, transition);
            return true;
        }
        return false;
    }

    public TMState getState(String name) {
        // loop through all states until a matching state name is found and return that
        // state, otherwise return null
        for (TMState state : states) {
            if (state.getName().equals(name))
                return state;
        }
        return null;
    }

    public TMTransition getToState(TMState from, char onSymb) { return from.getTransitions(onSymb); }
}

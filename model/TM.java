package model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class TM {

    private LinkedHashSet<TMState> states;
    private LinkedList<Character> tape;
    private int tapeHeadIndex;
    private Set<String> alphabet;

    public TM(int numStates, int alphabetSize) {
        this.states = new LinkedHashSet<>(numStates);
        this.tape = new LinkedList<>();
        this.tape.add('0');
        this.tapeHeadIndex = 0;
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

    public void addSigma(String symbol) {
        if (!symbol.equals("e")) {
            alphabet.add(symbol);
        }
    }

    public Set<String> getSigma() {
        return alphabet;
    }

    public boolean addTransitions(String fromState, String symbolRead, String toState, String symbolToWrite,
            String direction) {
        TMState from = getState(fromState);
        TMState to = getState(toState);

        if (!alphabet.contains(symbolRead)) {
            return false;
        }

        // if from state isn't in system stop and return false
        if (!states.contains(from) || !states.contains(to)) {
            return false;
        }
        TMTransition transition = new TMTransition(toState, symbolToWrite, direction);

        // if symbol is not in system alphabet or any states to or from are empty, stop
        // and return false,
        // otherwise add transitions for state
        if (from != null) {
            from.addTransitionToState(symbolRead, transition);
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

    public TMTransition getToState(TMState from, String onSymb) {
        return from.getTransitions(onSymb);
    }

    public void moveHead(String direction) {
        if (direction.equals("L")) {
            if (tapeHeadIndex == 0) {
                tape.addFirst('0');
            } else {
                tapeHeadIndex--;
            }
        } else if (direction.equals("R")) {
            tapeHeadIndex++;
            if (tapeHeadIndex == tape.size()) {
                tape.addLast('0');
            }
        }
    }

    public char readTape() {
        return tape.get(tapeHeadIndex);
    }

    public void writeTape(char c) {
        tape.set(tapeHeadIndex, c);
    }

    public void loadInput(String input) {
        tape.clear();
        tapeHeadIndex = 0;

        if (input == null || input.isEmpty()) {
            tape.add('0');
            return;
        }

        for (char c : input.toCharArray()) {
            tape.add(c);
        }
    }

    public int getSumOfSymbols() {
        int sum = 0;
        int n;
        for (char c : tape) {
            sum = Character.getNumericValue(c) + sum;
        }
        return sum;
    }

    public int getTapeLength() {
        return tape.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : tape) {
            sb.append(c);
        }
        return sb.toString();
    }
}

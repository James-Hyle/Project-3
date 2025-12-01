package model;

import java.util.*;

public class TM {

    private LinkedHashSet<TMState> states;
    private LinkedList<Character> tape;
    private ListIterator<Character> tapeIterator;
    private Set<String> alphabet;
    public int sum = 0;

    /**
     * Constructs a Turing Machine with the specified number of states and alphabet
     * size.
     *
     * @param numStates    the number of states in the machine
     * @param alphabetSize the size of the input alphabet
     */
    public TM(int numStates, int alphabetSize) {
        this.states = new LinkedHashSet<>(numStates);
        this.tape = new LinkedList<>();
        this.tape.add('0');
        this.alphabet = new LinkedHashSet<>(alphabetSize);
    }

    /**
     * Adds a new state to the Turing Machine if it does not already exist.
     *
     * @param state the state to add
     * @return true if the state was added, false otherwise
     */
    public boolean addState(TMState state) {
        // check states to prevent duplicate before creating and adding new state
        if (!states.contains(getState(state.getName()))) {
            return states.add(state);
        }
        return false;
    }

    /**
     * Marks the specified state as an accepting state.
     *
     * @param name the name of the state to mark as accepting
     * @return true if the state exists and was updated, false otherwise
     */
    public boolean setAccept(String name) {
        TMState temp = getState(name);
        // check for valid state
        if (states.contains(temp)) {
            temp.setAcceptState();
            return true;
        }
        return false;
    }

    /**
     * Add symbol to the machine's input alphabet
     *
     * @param symbol the symbol to add
     */
    public void addSigma(String symbol) {
            alphabet.add(symbol);
    }

    /**
     * Retrieve machine's input alphabet.
     *
     * @return the set of alphabet symbols
     */
    public Set<String> getSigma() {
        return alphabet;
    }

    /**
     * Add transition from one state to another based on a read symbol
     *
     * @param fromState     the name of the current state
     * @param symbolRead    the symbol read from the tape
     * @param toState       the destination state name
     * @param symbolToWrite the symbol to write to the tape
     * @param direction     the direction to move the head ("L" or "R")
     * @return true if the transition was successfully added, false otherwise
     */
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

    /**
     * Retrieves the TMState object with the given name
     *
     * @param name the name of the state
     * @return the TMState if found, otherwise null
     */
    public TMState getState(String name) {
        // loop through all states until a matching state name is found and return that
        // state, otherwise return null
        for (TMState state : states) {
            if (state.getName().equals(name))
                return state;
        }
        return null;
    }

    /**
     * Get transition from a given state on a specified symbol
     *
     * @param from   the state to transition from
     * @param onSymb the symbol read from the tape
     * @return the transition associated with the given symbol
     */
    public TMTransition getToState(TMState from, String onSymb) {
        return from.getTransitions(onSymb);
    }

    /**
     * Move tape head one position left or right, expanding if needed
     *
     * @param direction "L" for left or "R" for right
     */
    public void moveHead(String direction) {
        if (direction.equals("L")) {
            tapeIterator.previous();
            if (!tapeIterator.hasPrevious()) {
                tapeIterator.add('0');
                tapeIterator.previous();
            } else {
                tapeIterator.previous();
            }
        } else if (direction.equals("R")) {
            if (!tapeIterator.hasNext()) {
                tapeIterator.add('0');
                tapeIterator.previous();
            }
        }
    }

    /**
     * Read current symbol from the tape
     *
     * @return the symbol under the tape head
     */
    public char readTape() {
        return tapeIterator.next();
    }

    /**
     * Write specified character to the current tape cell
     *
     * @param c the character to write
     */
    public void writeTape(char c) {
        tapeIterator.set(c);
    }

    /**
     * Load input string onto the tape
     *
     * @param input the input string to load
     */
    public void loadInput(String input) {
        tape.clear();

        if (input == null || input.isEmpty()) {
            tape.add('0');
        } else {
            for (char c : input.toCharArray()) {
                sum = Character.getNumericValue(c) + sum;
                tape.add(c);
            }
        }
        tapeIterator = tape.listIterator();
    }

    /**
     * Return the number of cells currently on the tape
     *
     * @return the tape length
     */
    public int getTapeLength() {
        return tape.size();
    }

    /**
     * Convert tape content into String
     *
     * @return the tape as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : tape) {
            sb.append(c);
        }
        return sb.toString();
    }
}

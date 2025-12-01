package tm;

import java.util.LinkedHashMap;
import java.util.Set;

public class TMState {
    private String name;
    private boolean isAccept;
    private LinkedHashMap<String, TMTransition> delta;

    /**
     * Create new Turing Machine state with the given name.
     *
     * @param name the name/label of the state
     */
    public TMState(String name) {
        this.name = name;
        isAccept = false;
        delta = new LinkedHashMap<>();
    }

    /**
     * Sets this state as an accept state of the DFA
     *
     * @return true if successful
     */
    public boolean setAcceptState() {
        return this.isAccept = true;
    }

    /**
     * Returns true if this state is an accept state
     *
     * @return true if accept state
     */
    public boolean getAcceptState() {
        return this.isAccept;
    }

    /**
     * Gets the transitions for a particular character
     *
     * @return the TMTransition
     */
    public TMTransition getTransitions(String c) {
        return delta.get(c);
    }

    /**
     * getter for the string label
     *
     * @return returns the state label.
     */
    public String getName() {
        return name;
    }

    public Set<String> getTransitionSymbols() {
        return delta.keySet();
    }

    /**
     * Set the transitions for the state
     */
    public void addTransitionToState(String c, TMTransition t) {
        if (delta.containsKey(c)) {
            delta.replace(c, t);
        } else {
            delta.put(c, t);
        }
    }
}

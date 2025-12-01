package model;

public class TMTransition {
    private String to;
    private String writeSymbol;
    private String moveDirection;

    /**
     * Constructor for transition set in Turing Machine
     *
     * @param to            destination state name
     * @param writeSymbol   symbol to write to the tape
     * @param moveDirection direction to move the tape head
     */
    public TMTransition(String to, String writeSymbol, String moveDirection) {
        this.to = to;
        this.writeSymbol = writeSymbol;
        this.moveDirection = moveDirection;
    }

    /**
     * Updates the transition toState
     * 
     * @param to
     */
    public void updateToState(String to) {
        this.to = to;
    }

    /**
     * Updates the transition write character
     * 
     * @param writeSymbol
     */
    public void updateWriteSymbol(String writeSymbol) {
        this.writeSymbol = writeSymbol;
    }

    /**
     * Updates the transition move direction
     * 
     * @param moveDirection
     */
    public void updateMoveDirection(String moveDirection) {
        if (!moveDirection.equals("L") && !moveDirection.equals("R")) {
            return;
        }
        this.moveDirection = moveDirection;
    }

    /**
     * Gets the transition to state
     * 
     * @return to state
     */
    public String getTo() {
        return to;
    }

    /**
     * Gets the write symbol
     * 
     * @return write symbol
     */
    public String getWriteSymbol() {
        return writeSymbol;
    }

    /**
     * Gets the move direction
     * 
     * @return move direction
     */
    public String getMoveDirection() {
        return moveDirection;
    }
}

package model;

public class TMTransition {
    private String to;
    private String writeSymbol;
    private String moveDirection;

    public TMTransition(String to, String writeSymbol, String moveDirection) {
        this.to = to;
        this.writeSymbol = writeSymbol;
        this.moveDirection = moveDirection;
    }

    /**
     * Updates the transition toState
     * @param to
     */
    public void updateToState(String to) { this.to = to; }

    /**
     * Updates the transition write character
     * @param writeSymbol
     */
    public void updateWriteSymbol(String writeSymbol) { this.writeSymbol = writeSymbol; }
    /**
     * Updates the transition move direction
     * @param moveDirection
     */
    public void updateMoveDirection(String moveDirection) { this.moveDirection = moveDirection; }

    /**
     * Gets the transition to state
     * @return to state
     */
    public String getTo() { return to; }

    /**
     * Gets the write symbol
     * @return write symbol
     */
    public String getWriteSymbol() { return writeSymbol; }

    /**
     * Gets the move direction
     * @return move direction
     */
    public String getMoveDirection() { return moveDirection; }
}

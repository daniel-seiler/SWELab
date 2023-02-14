package iwwwdnw.ui.impl.commands;

public enum Output {
    NEW_LINE("\n"),
    
    TYPE_THROW("Type \"throw\" to throw the dices" + NEW_LINE),
    
    TYPE_MOVE("Type \"move [field_nr] [pawn_nr]\" to move a pawn" + NEW_LINE),
    
    TYPE_MOVE_TO_START("Type \"moveToStart\" to move a pawn to a start field" + NEW_LINE),
    
    TYPE_NEXT("Type \"next\" to start your turn" + NEW_LINE),
    
    INVALID_INPUT("Your input is unknown. Please use one of the available commands"),
    
    SEPARATOR("---------------------------------------------" + NEW_LINE),
    
    FINISHED_TURN("You finished your turn..." + NEW_LINE +
                         "Next player is: "),
    
    ASK_FOR_COMMAND(NEW_LINE + "What do you want to do next?"),
    
    CURRENT_PLAYER("Current player: ");
    
    private final String output;
    
    Output(final String output) {
        this.output = output;
    }
    
    @Override
    public String toString() {
        return output;
    }
}

package iwwwdnw.ui.impl.commands;

public enum Output {
    NEW_LINE("\n"),
    
    TYPE_HELP("Type \"help\" for help" + NEW_LINE),
    
    TYPE_THROW("Type \"throw\" to throw the dices" + NEW_LINE),
    
    TYPE_MOVE("Type \"move [pawn_nr] [field_nr]\" to move a pawn" + NEW_LINE),
    
    TYPE_MOVE_TO_START("Type \"moveToStart\" to move a pawn to a start field" + NEW_LINE),
    
    TYPE_NEXT("Type \"next\" to start your turn" + NEW_LINE),
    
    SEPARATOR("---------------------------------------------" + NEW_LINE),
    
    FINISHED_TURN("You finished your turn..." + NEW_LINE +
                         "Next player is: ");
    
    private final String output;
    
    Output(final String output) {
        this.output = output;
    }
}

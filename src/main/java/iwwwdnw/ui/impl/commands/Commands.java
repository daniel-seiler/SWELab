package iwwwdnw.ui.impl.commands;

import iwwwdnw.spielzug.impl.SpielzugImpl;
import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Pawn;
import iwwwdnw.spielzug.port.SpielzugPort;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    THROW_DICE("throw") {
        @Override
        public void execute(MatchResult matcher, SpielzugPort spielzugPort) {
            spielzugPort.spielzug().throwDice();
        }
    },
    
    PAWN_TO_START_FIELD("moveToStart") {
        @Override
        public void execute(MatchResult matcher, SpielzugPort spielzugPort) {
            spielzugPort.spielzug().pawnToStartField();
        }
    },
    
    MOVE_PAWN("move\\s\\d+\\s\\d+") {
        @Override
        public void execute(MatchResult matcher, SpielzugPort spielzugPort) {
            Field tmpField = ((SpielzugImpl) spielzugPort.spielzug()).getField(42);
            Pawn tmpPawn = ((SpielzugImpl) spielzugPort.spielzug()).getPawn(42);
            spielzugPort.spielzug().movePawn(tmpField, tmpPawn);
        }
    },
    
    FINISH_TURN("next") {
        @Override
        public void execute(MatchResult matcher, SpielzugPort spielzugPort) {
            spielzugPort.spielzug().finishTurn();
        }
    };
    
    private final Pattern pattern;
    
    Commands(final String command) {
        this.pattern = Pattern.compile(command);
    }
    
    public abstract void execute(MatchResult matcher, SpielzugPort spielzugPort);
    
    public static Commands executeMatching(final String input, SpielzugPort spielzugPort) throws InputException {
        for (Commands command: Commands.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                command.execute(matcher, spielzugPort);
                return command;
            }
        }
        throw new InputException("Command not found");
    }
}

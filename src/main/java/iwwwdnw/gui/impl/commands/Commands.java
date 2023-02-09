package iwwwdnw.gui.impl.commands;

import iwwwdnw.spielzug.impl.DiceResult;
import iwwwdnw.spielzug.impl.PawnImpl;
import iwwwdnw.spielzug.impl.SpielzugImpl;
import iwwwdnw.spielzug.impl.TurnInfo;
import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Spielzug;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    THROW_DICE("throw") {
        @Override
        public void execute(MatchResult matcher, Spielzug spielzug) {
            this.output = spielzug.throwDice().toString();
        }
    },
    
    PAWN_TO_START_FIELD("moveToStart") {
        @Override
        public void execute(MatchResult matcher, Spielzug spielzug) {
            //TODO output is coming from spielzug
            spielzug.pawnToStartField();
        }
    },
    
    MOVE_PAWN("move\\s\\d+\\s\\d+") {
        @Override
        public void execute(MatchResult matcher, Spielzug spielzug) {
            Field tmpField = ((SpielzugImpl) spielzug).getField(42);
            PawnImpl tmpPawn = ((SpielzugImpl) spielzug).getPawn(42);
            this.output = spielzug.movePawn(tmpField, tmpPawn).toString();
        }
    },
    
    FINISH_TURN("finish") {
        @Override
        public void execute(MatchResult matcher, Spielzug spielzug) {
            //TODO return next player coming
            spielzug.finishTurn();
        }
    },
    
    HELP("*") {
        @Override
        public void execute(MatchResult matcher, Spielzug spielzug) {
            //TODO
            this.output = "Help message";
        }
    };
    
    
    protected String output;
    private final Pattern pattern;
    
    Commands(final String command) {
        this.pattern = Pattern.compile(command);
    }
    
    public abstract void execute(MatchResult matcher, Spielzug spielzug);
    
    public static Commands executeMatching(final String input, Spielzug spielzug) throws InputException {
        for (Commands command: Commands.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                command.execute(matcher, spielzug);
                return command;
            }
        }
        throw new InputException("Command not found");
    }
    
    @Override
    public String toString() {
        return this.output;
    }
}

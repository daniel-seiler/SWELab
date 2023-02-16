package iwwwdnw.spielzug.impl;

import java.util.*;

import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Field.FieldType;
import iwwwdnw.spielzug.port.Pawn;

public class Board {




	/**
	* @link composition
	*/
	private iwwwdnw.spielzug.impl.HomeField lnkHomeField;
	/**
	* @link composition
	*/
	private iwwwdnw.spielzug.impl.StartField lnkStartField;
	/**
	* @link composition
	*/
	private iwwwdnw.spielzug.impl.BoardField lnkBoardField;
	public static final int NUM_PLAYER   = 4;
    public static final int NUM_PAWNS = 5;
    public static final int FIELDS_BEWTWEEN = 9;
    public static final int FIELDS_TOTAL = NUM_PLAYER * FIELDS_BEWTWEEN + NUM_PLAYER;

    private static Board instance;

    private final List<PlayerImpl> players = new ArrayList<>();
    private final List<Field> gameFields = new ArrayList<Field>();
    private final Map<PlayerImpl, StartField> startFields = new HashMap<PlayerImpl, StartField>();

    Board() {
        players.add(new PlayerImpl(Colour.RED, "Rot", 0, initHome()));
        players.add(new PlayerImpl(Colour.YELLOW, "Gelb", 1, initHome()));
        players.add(new PlayerImpl(Colour.GREEN, "Gruen", 2, initHome()));
        players.add(new PlayerImpl(Colour.BLUE, "Blau", 3, initHome()));

        initBoardPositions();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public List<PlayerImpl> getPlayers() {
        return players;
    }
    
    public Field getStartField(PlayerImpl player) {
    	return startFields.get(player);
    }
    
    public Field getField(int fieldId) {
    	return gameFields.get(fieldId);
    }
    
    public List<Field> getFieldsInRange(int first, int second) {
    	int max = Math.max(first, second);
    	int min = Math.min(first, second);
    	List<Field> fields = new ArrayList<Field>();
    	if (max - min < min + FIELDS_TOTAL - max) {
    		for (int i = min; i <= max; i++) {
    			fields.add(gameFields.get(i));
    		}
    	} else {
    		for (int i = max; i <= min + FIELDS_TOTAL; i++) {
    			fields.add(gameFields.get(i % FIELDS_TOTAL));
    		}
    	}
    	return fields;
    }

    private void initBoardPositions() {
    	for (PlayerImpl player : players) {
    		int offset = player.getStartPosition() * FIELDS_BEWTWEEN + player.getStartPosition();
    		StartField startField = new StartField(offset);
            player.getPawns().get(0).setCurrentField(startField);
    		gameFields.add(startField);
    		startFields.put(player, startField);
    		for (int index = 0; index < FIELDS_BEWTWEEN; index++) {
                gameFields.add(new BoardField(offset + 1 + index));
            }
    	}
        
    }

  
    private List<Pawn> initHome() {
    	List<Pawn> pawns = new ArrayList<>();
        for (int count = 0; count < NUM_PAWNS; count++) {
            pawns.add(new PawnImpl(new HomeField()));
        }
        return pawns;
    }
    
    @Override
	public String toString() {
    	StringBuilder board = new StringBuilder();
    	for (int i = 0; i < FIELDS_TOTAL; i++) {
            for (PlayerImpl player : players) {
                /*if (player.getStartPosition() * FIELDS_BEWTWEEN + player.getStartPosition() == i) {
                    board.append("[S_");
                    board.append(player.getColour());
                    board.append("] ");
                }*/
                for (int f = 0; f < player.getPawns().size(); f++) {
                    if (player.getPawns().get(f).getCurrentField() == gameFields.get(i)) {
                        board.append("(");
                        board.append(player.getColour());
                        board.append("-");
                        board.append(f);
                        board.append(") ");
                    }
                }
            }
            board.append(gameFields.get(i).getFieldID()).append("|");
        }
        board.append("\n");
        return board.toString();
    }
}

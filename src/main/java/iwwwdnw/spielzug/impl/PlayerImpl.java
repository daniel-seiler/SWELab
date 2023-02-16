package iwwwdnw.spielzug.impl;

import java.util.List;

import iwwwdnw.spielzug.port.Pawn;


public class PlayerImpl {
	
	
	/**
	 * @link aggregation
	 */
	
	private final Colour colour;
    private final String name;
    private final int startPosition;
    private final List<Pawn> pawns;

    public PlayerImpl(Colour colour, String name, int startPosition, List<Pawn> pawns) {
        this.colour = colour;
        this.name = name;
        this.startPosition = startPosition;
        this.pawns = pawns;
    }

    public Colour getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public int getStartPosition() {
        return startPosition;
    }
    
    public List<Pawn> getPawns() {
    	return pawns;
    }
    
    @Override
    public String toString() {
    	return colour.name();
    }
    
    
}

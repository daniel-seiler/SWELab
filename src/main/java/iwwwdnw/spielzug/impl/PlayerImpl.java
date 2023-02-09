package iwwwdnw.spielzug.impl;

import java.util.List;


public class PlayerImpl {
	
	private final Colour colour;
    private final String name;
    private final int startPosition;
    private final List<PawnImpl> pawns;

    public PlayerImpl(Colour colour, String name, int startPosition, List<PawnImpl> pawns) {
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
    
    public List<PawnImpl> getPawns() {
    	return pawns;
    }
    
    @Override
    public String toString() {
    	return colour.name();
    }
    
    
}

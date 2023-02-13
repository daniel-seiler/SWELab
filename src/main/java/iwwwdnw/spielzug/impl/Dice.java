package iwwwdnw.spielzug.impl;

import java.util.Random;

public class Dice {

    public static final  int    MAX_ROLL = 6;
    private static final Random random   = new Random();
    private int numThrows = 0;

    public DiceResult rollTwo() {
    	numThrows++;
        int r1 =  random.nextInt(MAX_ROLL) + 1;
        return new DiceResult(/*r1 + (random.nextInt(MAX_ROLL) + 1)*/ 7);
    }
    
    public int getNumThrows() {
    	return numThrows;
    }

    
}
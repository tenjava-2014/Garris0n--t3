package nu.gar.plague.util;

import java.util.Random;

public class VariableIntegerRandom extends VariableInteger{

    private Random random;
    private int bottom;
    private int top;

    public VariableIntegerRandom(int bottom, int top){

        this.random = new Random();
        this.bottom = bottom;
        this.top = top;

    }

    @Override
    public int getInt(){

        return random.nextInt(top - bottom) + bottom;

    }
}

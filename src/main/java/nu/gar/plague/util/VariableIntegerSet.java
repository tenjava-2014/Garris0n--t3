package nu.gar.plague.util;

public class VariableIntegerSet extends VariableInteger{

    private int value;

    public VariableIntegerSet(int value){

        this.value = value;

    }

    @Override
    public int getInt(){

        return value;

    }
}

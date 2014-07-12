package nu.gar.plague.util;

import nu.gar.plague.exceptions.PlagueFailedToLoadException;

import java.util.regex.Pattern;

public abstract class VariableInteger{

    private static final String DELIMITER = Pattern.quote("|");

    public abstract int getInt();

    public static VariableInteger create(Object object){

        if(object instanceof Integer){

            return new VariableIntegerSet((Integer) object);

        }
        if(object instanceof String){

            String string = (String) object;

            String[] split = string.split(DELIMITER);

            if(split.length != 2)
                throw new PlagueFailedToLoadException("Random number notation is \"n|n\". Received \"" + string + "\".");

            int bottom;
            int top;

            try{

                bottom = Integer.parseInt(split[0]);
                top = Integer.parseInt(split[1]);

            }
            catch(NumberFormatException e){

                throw new PlagueFailedToLoadException("Random number notation is \"n|n\". Received \"" + string + "\".");

            }

            if(bottom >= top)
                throw new PlagueFailedToLoadException("Random number notation (n|n) requires " +
                        "that the second number be greater than the first.");

            return new VariableIntegerRandom(bottom, top);

        }

        throw new PlagueFailedToLoadException("Attempted to parse a number or random number, received " + object.toString() + ".");

    }

}

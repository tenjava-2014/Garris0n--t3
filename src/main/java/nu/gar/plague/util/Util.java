package nu.gar.plague.util;

import org.bukkit.*;

import java.util.regex.Pattern;

public class Util{

    public static Location parseLocation(String string){

        String[] split = string.split(Pattern.quote("|"));

        if(split.length < 4)
            return null;

        World world = Bukkit.getServer().getWorld(split[0]);

        if(world == null)
            return null;

        double x;
        double y;
        double z;

        try{

            x = Double.parseDouble(split[1]);
            y = Double.parseDouble(split[2]);
            z = Double.parseDouble(split[3]);

        }
        catch(NumberFormatException e){

            return null;

        }

        return new Location(world, x, y, z);

    }

}

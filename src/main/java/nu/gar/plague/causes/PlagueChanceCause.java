package nu.gar.plague.causes;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import nu.gar.plague.util.Util;
import org.bukkit.configuration.ConfigurationSection;

public abstract class PlagueChanceCause extends PlagueCause{

    private int chance;

    public PlagueChanceCause(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Cause \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        this.chance = section.getInt(Key.CHANCE.getString());

    }

    public boolean chance(){

        return Util.chance(chance);

    }

    private static enum Key{

        CHANCE("chance");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

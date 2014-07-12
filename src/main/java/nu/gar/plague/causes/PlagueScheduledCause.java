package nu.gar.plague.causes;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import nu.gar.plague.util.VariableInteger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

public class PlagueScheduledCause extends PlagueCause{

    private VariableInteger initialDelay;
    private VariableInteger frequency;

    public PlagueScheduledCause(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Cause \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        this.initialDelay = VariableInteger.create(section.get(Key.INITIAL_DELAY.getString()));
        this.frequency = VariableInteger.create(section.get(Key.FREQUENCY.getString()));

    }

    public int getInitialDelay(){

        return initialDelay.getInt();

    }

    public int getFrequency(){

        return frequency.getInt();

    }

    public void run(BukkitRunnable runnable){

        if(frequency.getInt() == -1)
            runnable.runTaskLater(getPlugin(), getInitialDelay());
        else
            runnable.runTaskTimer(getPlugin(), getInitialDelay(), getFrequency());

    }

    private static enum Key{

        INITIAL_DELAY("initial-delay"),
        FREQUENCY("frequency");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }


}

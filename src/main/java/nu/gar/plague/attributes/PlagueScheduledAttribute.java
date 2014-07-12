package nu.gar.plague.attributes;

import nu.gar.plague.Main;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import nu.gar.plague.util.VariableInteger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class PlagueScheduledAttribute extends PlagueAttribute{

    private Set<BukkitRunnable> runnables;

    private VariableInteger initialDelay;
    private VariableInteger frequency;

    public PlagueScheduledAttribute(Main plugin, ConfigurationSection section){

        super(plugin);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Attribute " + section.getName() +
                        "is  missing a required option: " + k + ".");

        this.initialDelay = VariableInteger.create(section.get(Key.INITIAL_DELAY.getString()));
        this.frequency = VariableInteger.create(section.get(Key.FREQUENCY.getString()));

    }

    public int getInitialDelay(){

        return initialDelay.getInt();

    }

    public int getFrequency(){

        return frequency.getInt();

    }

    public Set<BukkitRunnable> getRunnables(){

        return runnables;

    }

    public void run(BukkitRunnable runnable){

        if(frequency.getInt() == -1)
            runnable.runTaskLater(getPlugin(), getInitialDelay());
        else
            runnable.runTaskTimer(getPlugin(), getInitialDelay(), getFrequency());

        getRunnables().add(runnable);

    }

    @Override
    public void stop(){

        getRunnables().clear();

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

package nu.gar.plague.causes.type;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.PlagueChanceCause;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class CauseContagion extends PlagueChanceCause{

    private double radiusSquared;

    public CauseContagion(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague, section);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Cause \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        radiusSquared = Math.pow(section.getDouble(Key.RADIUS.getString()), 2);

        new BukkitRunnable(){

            @Override
            public void run(){

                List<LivingEntity> infected = getPlague().getInfectedEntities();

                for(LivingEntity e : getPlague().getInfectableEntities())
                    if(nearInfected(e, infected))
                        getPlague().infect(e);

            }
        }.runTaskTimer(getPlugin(), 0, 20);

    }

    private boolean nearInfected(LivingEntity entity, List<LivingEntity> infected){

        for(LivingEntity e : infected)
            if(e.getLocation().distanceSquared(entity.getLocation()) <= radiusSquared)
                return true;

        return false;

    }

    private static enum Key{

        RADIUS("radius");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

package nu.gar.plague.causes.type;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.PlagueCause;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import nu.gar.plague.util.Util;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class CauseLocationRadius extends PlagueCause{

    private Location center;
    private double radiusSquared;

    public CauseLocationRadius(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Cause \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        center = Util.parseLocation(section.getString(Key.CENTER.getString()));

        if(center == null)
            throw new PlagueFailedToLoadException("Location is improperly formatted.");

        radiusSquared = Math.pow(section.getDouble(Key.RADIUS.getString()), 2);

        new BukkitRunnable(){

            @Override
            public void run(){

                for(LivingEntity e : getPlague().getInfectableEntities())
                    if(e.getLocation().distanceSquared(center) <= radiusSquared)
                        getPlague().infect(e);

            }
        }.runTaskTimer(getPlugin(), 0, 20);

    }

    private static enum Key{

        CENTER("center"),
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

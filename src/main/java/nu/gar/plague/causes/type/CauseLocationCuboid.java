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

public class CauseLocationCuboid extends PlagueCause{

    private Location loc1;
    private Location loc2;

    public CauseLocationCuboid(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Cause \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        loc1 = Util.parseLocation(section.getString(Key.CORNER_ONE.getString()));
        loc2 = Util.parseLocation(section.getString(Key.CORNER_TWO.getString()));

        if(loc1 == null || loc2 == null)
            throw new PlagueFailedToLoadException("Location is improperly formatted.");

        new BukkitRunnable(){

            @Override
            public void run(){

                for(LivingEntity e : getPlague().getInfectableEntities())
                    if(inside(e))
                        getPlague().infect(e);

            }
        }.runTaskTimer(getPlugin(), 0, 20);

    }

    private boolean inside(LivingEntity entity){

        double minX = Math.min(loc1.getX(), loc2.getX());
        double maxX = Math.max(loc1.getX(), loc2.getX());

        double minY = Math.min(loc1.getY(), loc2.getY());
        double maxY = Math.max(loc1.getY(), loc2.getY());

        double minZ = Math.min(loc1.getZ(), loc2.getZ());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        Location location = entity.getLocation();

        return location.getX() >= minX && location.getX() <= maxX &&
                location.getY() >= minY && location.getY() <= maxY &&
                location.getZ() >= minZ && location.getZ() <= maxZ;

    }

    private static enum Key{

        CORNER_ONE("corner1"),
        CORNER_TWO("corner2");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

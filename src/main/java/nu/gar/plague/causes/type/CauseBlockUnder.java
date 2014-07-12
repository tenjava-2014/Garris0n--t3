package nu.gar.plague.causes.type;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.PlagueChanceCause;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class CauseBlockUnder extends PlagueChanceCause{

    private Material material;

    public CauseBlockUnder(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague, section);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Cause \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        material = Material.valueOf(section.getString(Key.BLOCK.getString()));

        if(material == null || !material.isBlock())
            throw new PlagueFailedToLoadException("The block \"" +
                    section.getStringList(Key.BLOCK.getString()) + "\" is not valid.");

        new BukkitRunnable(){

            @Override
            public void run(){

                for(LivingEntity e : getPlague().getInfectableEntities())
                    if(standingOn(e))
                        getPlague().infect(e);

            }
        }.runTaskTimer(getPlugin(), 0, 10);

    }

    private boolean standingOn(LivingEntity entity){

        return entity.getLocation().subtract(0, 0.1, 0).getBlock().getType().equals(material);

    }

    private static enum Key{

        BLOCK("block");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

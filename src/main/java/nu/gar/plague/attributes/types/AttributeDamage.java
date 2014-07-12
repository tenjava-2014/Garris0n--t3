package nu.gar.plague.attributes.types;

import nu.gar.plague.Main;
import nu.gar.plague.attributes.PlagueScheduledAttribute;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class AttributeDamage extends PlagueScheduledAttribute{

    private double damage;

    public AttributeDamage(Main plugin, ConfigurationSection section){

        super(plugin, section);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Attribute \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        this.damage = section.getDouble(Key.DAMAGE.getString());

    }

    public double getDamage(){

        return damage;

    }

    @Override
    public void giveSymptoms(LivingEntity entity){

        BukkitRunnable runnable = new EntityRunnable(entity){

            @Override
            public void run(LivingEntity entity){

                entity.damage(getDamage());

            }

            @Override
            public void entityGone(){

                getRunnables().remove(this);

            }
        };

        run(runnable);

    }

    private static enum Key{

        DAMAGE("damage");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

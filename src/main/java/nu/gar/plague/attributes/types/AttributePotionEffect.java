package nu.gar.plague.attributes.types;

import nu.gar.plague.Main;
import nu.gar.plague.attributes.PlagueScheduledAttribute;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class AttributePotionEffect extends PlagueScheduledAttribute{

    private PotionEffectType effectType;
    private int duration;
    private int amplifier;
    private boolean ambient;

    public AttributePotionEffect(Main plugin, ConfigurationSection section){

        super(plugin, section);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Attribute \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        effectType = PotionEffectType.getByName(section.getString(Key.EFFECT_TYPE.getString()));

        if(effectType == null)
            throw new PlagueFailedToLoadException("The potion effect \"" +
                    section.getString(Key.EFFECT_TYPE.getString()) + "\" is invalid.");

        duration = section.getInt(Key.DURATION.getString());
        amplifier = section.getInt(Key.AMPLIFIER.getString());
        ambient = section.getBoolean(Key.AMPLIFIER.getString());

    }

    public PotionEffect getPotionEffect(){

        return new PotionEffect(effectType, duration, amplifier, ambient);

    }

    @Override
    public void giveSymptoms(LivingEntity entity){

        BukkitRunnable runnable = new EntityRunnable(entity){

            @Override
            public void run(LivingEntity entity){

                entity.addPotionEffect(getPotionEffect());

            }

            @Override
            public void entityGone(){

                getRunnables().remove(this);

            }
        };

        run(runnable);

    }

    private static enum Key{

        EFFECT_TYPE("effect"),
        DURATION("duration"),
        AMPLIFIER("amplifier"),
        AMBIENT("ambient");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

package nu.gar.plague.attributes.types;

import nu.gar.plague.Main;
import nu.gar.plague.attributes.PlagueAttribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class AttributePoison extends PlagueAttribute{

    private Set<EntityRunnable> runnables;

    private int duration;
    private int amplifier;

    private int initialDelay;
    private int frequency;

    public AttributePoison(Main plugin, int duration, int amplifier, int initialDelay, int frequency){

        super(plugin);

        this.runnables = new HashSet<>();

        this.duration = duration;
        this.amplifier = amplifier;

        this.initialDelay = initialDelay;
        this.frequency = frequency;

    }

    @Override
    public void giveSymptoms(LivingEntity entity){

        EntityRunnable runnable = new EntityRunnable(entity){

            @Override
            public void run(LivingEntity entity){

                entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, amplifier, false));

            }

            @Override
            public void entityGone(){

                runnables.remove(this);

            }
        };

        runnable.runTaskTimer(getPlugin(), initialDelay, frequency);

        runnables.add(runnable);

    }

    @Override
    public void stop(){

        Iterator<EntityRunnable> iterator = runnables.iterator();

        while(iterator.hasNext()){

            iterator.next().cancel();
            iterator.remove();

        }

    }

}

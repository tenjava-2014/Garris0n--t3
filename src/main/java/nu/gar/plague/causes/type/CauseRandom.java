package nu.gar.plague.causes.type;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.PlagueScheduledCause;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class CauseRandom extends PlagueScheduledCause{

    private Random random;

    public CauseRandom(Main plugin, Plague plague, ConfigurationSection section){

        super(plugin, plague, section);

        this.random = new Random();

        BukkitRunnable runnable = new BukkitRunnable(){

            @Override
            public void run(){

                LivingEntity entity = getRandomEntity();

                if(entity != null)
                    getPlague().infect(entity);

            }
        };

        run(runnable);

    }

    private LivingEntity getRandomEntity(){

        List<Entity> entities = getPlague().getInfectableEntities();

        if(entities.isEmpty())
            return null;

        return (LivingEntity) entities.get(random.nextInt(entities.size()));

    }

}

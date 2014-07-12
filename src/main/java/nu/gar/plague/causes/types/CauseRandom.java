package nu.gar.plague.causes.types;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.PlagueCause;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class CauseRandom extends PlagueCause{

    public CauseRandom(Main plugin, Plague plague, int initialDelay, int frequency){

        super(plugin, plague);

        final Random random = new Random();

        new BukkitRunnable(){

            @Override
            public void run(){

                List<Entity> entities = getPlague().getVulnerableEntities();

                if(entities.isEmpty())
                    return;

                LivingEntity entity = (LivingEntity) entities.get(random.nextInt(entities.size()));

                getPlague().infect(entity);

            }
        }.runTaskTimer(getPlugin(), initialDelay, frequency);

    }

}

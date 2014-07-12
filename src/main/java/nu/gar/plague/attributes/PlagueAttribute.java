package nu.gar.plague.attributes;

import nu.gar.plague.Main;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class PlagueAttribute{

    private Main plugin;

    public PlagueAttribute(Main plugin){

        this.plugin = plugin;

    }

    public Main getPlugin(){

        return plugin;

    }

    public abstract void giveSymptoms(LivingEntity entity);

    public abstract void stop();

    public static abstract class EntityRunnable extends BukkitRunnable{

        private LivingEntity entity;

        public EntityRunnable(LivingEntity entity){

            this.entity = entity;

        }

        @Override
        public void run(){

            if((!(entity instanceof Player) && entity.isDead()) ||
                    (entity instanceof Player && !((Player) entity).isOnline())){

                this.cancel();
                this.entityGone();
                return;

            }

            run(entity);

        }

        public abstract void run(LivingEntity entity);

        public abstract void entityGone();

    }

}

package nu.gar.plague.attributes;

import nu.gar.plague.Main;
import org.bukkit.entity.LivingEntity;

public abstract class PlagueAttribute{

    private Main plugin;

    public PlagueAttribute(Main plugin){

        this.plugin = plugin;

    }

    public Main getPlugin(){

        return plugin;

    }

    public abstract void giveSymptoms(LivingEntity entity);

}

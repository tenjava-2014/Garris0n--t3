package nu.gar.plague.attributes;

import nu.gar.plague.Main;
import org.bukkit.entity.Entity;

public abstract class PlagueAttribute{

    private Main plugin;

    public PlagueAttribute(Main plugin){

        this.plugin = plugin;

    }

    public abstract void giveSymptoms(Entity entity);

}

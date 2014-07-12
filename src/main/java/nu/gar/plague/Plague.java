package nu.gar.plague;

import nu.gar.plague.attributes.PlagueAttribute;
import nu.gar.plague.causes.PlagueCause;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;

import java.util.List;

public class Plague{

    private Main plugin;

    private List<EntityType> vulnerable;

    private List<PlagueAttribute> attributes;
    private List<PlagueCause> causes;

    public Plague(Main plugin, ConfigurationSection section){

        this.plugin = plugin;
        //TODO: Load

    }

    public void apply(LivingEntity entity){

        if(!isVulnerable(entity))
            return;

        for(PlagueAttribute a : attributes)
            a.giveSymptoms(entity);

    }

    public void stop(){

        for(PlagueAttribute pa : attributes)
            pa.stop();

    }

    public List<EntityType> getVulnerableEntities(){

        return vulnerable;

    }

    public boolean isVulnerable(EntityType type){

        return getVulnerableEntities().contains(type);

    }

    public boolean isVulnerable(Entity entity){

        return isVulnerable(entity.getType());

    }

    private boolean validType(EntityType type){

        return type.getEntityClass().isAssignableFrom(LivingEntity.class);

    }

}

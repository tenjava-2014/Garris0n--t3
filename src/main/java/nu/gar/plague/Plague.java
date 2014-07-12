package nu.gar.plague;

import nu.gar.plague.attributes.PlagueAttribute;
import nu.gar.plague.causes.PlagueCause;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;

public class Plague{

    private List<EntityType> vulnerable;

    private List<PlagueAttribute> attributes;
    private List<PlagueCause> causes;

    public Plague(ConfigurationSection section){

        //TODO: Load

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

}

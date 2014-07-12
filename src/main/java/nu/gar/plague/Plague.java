package nu.gar.plague;

import nu.gar.plague.attributes.PlagueAttribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

import java.util.List;

public class Plague{

    private List<Class<? extends Entity>> vulnerable;

    private List<PlagueAttribute> attributes;

    public Plague(ConfigurationSection section){

        //TODO: Load

    }

    public List<Class<? extends Entity>> getVulnerableEntities(){

        return vulnerable;

    }

    public boolean isVulnerable(Class<? extends Entity> clazz){

        return getVulnerableEntities().contains(clazz);

    }

    public boolean isVulnerable(Entity entity){

        return isVulnerable(entity.getClass());

    }

}

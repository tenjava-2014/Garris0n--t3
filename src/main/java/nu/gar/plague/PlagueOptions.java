package nu.gar.plague;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.List;

public class PlagueOptions{

    private List<EntityType> vulnerable;
    private List<String> worlds;

    public PlagueOptions(ConfigurationSection section){

        //TODO: Load

    }

    public PlagueOptions(List<EntityType> vulnerable, List<String> worlds){

        this.vulnerable = vulnerable;
        this.worlds = worlds;

    }

    public List<EntityType> getVulnerable(){

        return vulnerable;

    }

    public List<String> getWorlds(){

        return worlds;

    }
}

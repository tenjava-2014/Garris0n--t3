package nu.gar.plague;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.List;

public class PlagueOptions{

    private String displayName;
    private List<EntityType> vulnerable;
    private List<String> worlds;

    public PlagueOptions(ConfigurationSection section){

        //TODO: Load

    }

    public PlagueOptions(String displayName, List<EntityType> vulnerable, List<String> worlds){

        this.displayName = displayName;
        this.vulnerable = vulnerable;
        this.worlds = worlds;

    }

    public String getDisplayName(){

        return displayName;

    }

    public List<EntityType> getVulnerable(){

        return vulnerable;

    }

    public List<String> getWorlds(){

        return worlds;

    }
}

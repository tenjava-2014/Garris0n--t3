package nu.gar.plague;

import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class PlagueOptions{

    private String displayName;
    private List<String> worlds;
    private List<EntityType> vulnerable;

    public PlagueOptions(ConfigurationSection section){

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Missing a required option: " + k + ".");

        this.displayName = section.getString(Key.DISPLAY_NAME.getString());
        this.worlds = section.getStringList(Key.WORLDS.getString());

        this.vulnerable = new ArrayList<>();

        for(String s : section.getStringList(Key.VULNERABLE.getString())){

            EntityType type = EntityType.valueOf(s);

            if(type == null)
                throw new PlagueFailedToLoadException("Entity Type \"" + s + "\" is invalid.");

            vulnerable.add(type);

        }

    }

    public PlagueOptions(String displayName, List<EntityType> vulnerable, List<String> worlds){

        this.displayName = displayName;
        this.vulnerable = vulnerable;
        this.worlds = worlds;

    }

    public String getDisplayName(){

        return displayName;

    }

    public List<String> getWorlds(){

        return worlds;

    }

    public List<EntityType> getVulnerable(){

        return vulnerable;

    }

    private static enum Key{

        DISPLAY_NAME("display-name"),
        WORLDS("enabled-worlds"),
        VULNERABLE("vulnerable-entity-types");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

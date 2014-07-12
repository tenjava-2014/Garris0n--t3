package nu.gar.plague;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class PlagueOptions{

    private String displayName;
    private List<String> worlds;
    private List<EntityType> vulnerable;

    public PlagueOptions(ConfigurationSection section){

        this.displayName = section.getString(Key.DISPLAY_NAME.getString());
        this.worlds = section.getStringList(Key.WORLDS.getString());
        this.vulnerable = new ArrayList<>();

        for(String s : section.getStringList(Key.VULNERABLE.getString())){

            EntityType type = EntityType.valueOf(s);

            if(type == null)
                return; //TODO: handle

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

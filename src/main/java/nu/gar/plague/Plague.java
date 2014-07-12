package nu.gar.plague;

import nu.gar.plague.attributes.PlagueAttribute;
import nu.gar.plague.attributes.types.AttributePoison;
import nu.gar.plague.causes.PlagueCause;
import nu.gar.plague.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;

import java.util.*;

public class Plague{

    private Main plugin;

    private Set<EntityType> vulnerable;

    private Set<PlagueAttribute> attributes;
    private Set<PlagueCause> causes;

    private List<String> worlds;

    public Plague(Main plugin, ConfigurationSection section){

        this.plugin = plugin;
        //TODO: Load

    }

    //TODO: FOR TESTING ONLY, WILL BE REMOVED
    public Plague(){

        this.vulnerable = new HashSet<>();

        this.attributes = new HashSet<>();
        this.causes = new HashSet<>();

        this.worlds = new ArrayList<>();

        this.vulnerable.add(EntityType.PLAYER);

        this.attributes.add(new AttributePoison(plugin, 100, 1, 0, 99999)); //give poison once

    }

    public void infect(LivingEntity entity){

        if(!isVulnerable(entity))
            return;

        for(PlagueAttribute a : attributes)
            a.giveSymptoms(entity);

    }

    public void stop(){

        for(PlagueAttribute pa : attributes)
            pa.stop();

    }

    public List<Entity> getVulnerableEntities(){

        return Util.getEntities(getVulnerableEntityTypes(), getAffectedWorlds());

    }

    public Set<World> getAffectedWorlds(){

        Set<World> worldSet = new HashSet<>();

        for(String s : worlds){

            World w = Bukkit.getServer().getWorld(s);

            if(s != null)
                worldSet.add(w);

        }

        return worldSet;

    }

    public Set<EntityType> getVulnerableEntityTypes(){

        return vulnerable;

    }

    public boolean isVulnerable(EntityType type){

        return getVulnerableEntityTypes().contains(type);

    }

    public boolean isVulnerable(Entity entity){

        return isVulnerable(entity.getType());

    }

    private boolean validType(EntityType type){

        return type.getEntityClass().isAssignableFrom(LivingEntity.class);

    }

}

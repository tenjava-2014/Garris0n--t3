package nu.gar.plague;

import nu.gar.plague.attributes.PlagueAttribute;
import nu.gar.plague.attributes.types.AttributePoison;
import nu.gar.plague.causes.PlagueCause;
import nu.gar.plague.causes.types.CauseRandom;
import nu.gar.plague.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;

import java.util.*;

public class Plague{

    private Main plugin;

    private PlagueOptions options;

    private Set<PlagueAttribute> attributes;
    private Set<PlagueCause> causes;

    private Set<UUID> infected;

    public Plague(Main plugin, ConfigurationSection section){

        this.plugin = plugin;
        //TODO: Load

    }

    //TODO: FOR TESTING ONLY, WILL BE REMOVED
    public Plague(Main plugin){

        this.plugin = plugin;

        this.options = new PlagueOptions(Arrays.asList(EntityType.PIG), Arrays.asList(Bukkit.getServer().getWorlds().iterator().next().getName())); //it's only for testing ._.

        this.attributes = new HashSet<>();
        this.causes = new HashSet<>();

        this.attributes.add(new AttributePoison(plugin, 200, 1, 0, 5));
        this.causes.add(new CauseRandom(plugin, this, 0, 100));

    }

    public void infect(LivingEntity entity){

        if(!isVulnerable(entity))
            return;

        if(getInfected().contains(entity.getUniqueId()))
            return;

        infected.add(entity.getUniqueId());

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

    public Set<UUID> getInfected(){

        return infected;

    }

    public List<World> getAffectedWorlds(){

        List<World> worldSet = new ArrayList<>();

        for(String s : options.getWorlds()){

            World w = Bukkit.getServer().getWorld(s);

            if(s != null)
                worldSet.add(w);

        }

        return worldSet;

    }

    public List<EntityType> getVulnerableEntityTypes(){

        return options.getVulnerable();

    }

    public boolean isVulnerable(EntityType type){

        return getVulnerableEntityTypes().contains(type);

    }

    public boolean isVulnerable(Entity entity){

        return options.getWorlds().contains(entity.getWorld().getName()) && isVulnerable(entity.getType());

    }

    private boolean validType(EntityType type){

        return type.getEntityClass().isAssignableFrom(LivingEntity.class);

    }

}

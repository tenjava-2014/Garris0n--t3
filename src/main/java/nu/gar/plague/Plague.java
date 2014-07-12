package nu.gar.plague;

import nu.gar.plague.attributes.AttributeType;
import nu.gar.plague.attributes.PlagueAttribute;
import nu.gar.plague.causes.CauseType;
import nu.gar.plague.causes.PlagueCause;
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

        this.options = new PlagueOptions(section.getConfigurationSection(Key.OPTIONS.getString()));

        this.attributes = AttributeType.getAttributes(plugin, section.getConfigurationSection(Key.ATTRIBUTES.getString()));
        this.causes = CauseType.getCauses(plugin, this, section.getConfigurationSection(Key.CAUSES.getString()));

        this.infected = new HashSet<>();

    }

    public void infect(LivingEntity entity){

        if(!isVulnerable(entity) || isInfected(entity))
            return;

        infected.add(entity.getUniqueId());

        for(PlagueAttribute a : attributes)
            a.giveSymptoms(entity);

        plugin.debug("Infected " + entity.toString() + " with " + getDisplayName() + ".");

    }

    public void stop(){

        for(PlagueAttribute pa : attributes)
            pa.stop();

        getInfected().clear();

    }

    public String getDisplayName(){

        return options.getDisplayName();

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

    public boolean isInfected(Entity entity){

        return getInfected().contains(entity.getUniqueId());

    }

    private static enum Key{

        OPTIONS("options"),
        ATTRIBUTES("attributes"),
        CAUSES("causes");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

package nu.gar.plague.causes;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.type.*;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public enum CauseType{

    RANDOM("random", CauseRandom.class),
    LOCATION_RADIUS("location-radius", CauseLocationRadius.class),
    LOCATION_CUBOID("location-cuboid", CauseLocationCuboid.class),
    BLOCK_UNDER("block-under", CauseBlockUnder.class),
    CONTAGION("contagion", CauseContagion.class);

    private String key;
    private Class<? extends PlagueCause> clazz;

    CauseType(String key, Class<? extends PlagueCause> clazz){

        this.key = key;
        this.clazz = clazz;

    }

    public String getKey(){

        return key;

    }

    public Class<? extends PlagueCause> getClazz(){

        return clazz;

    }

    public PlagueCause create(Main plugin, Plague plague, ConfigurationSection section){

        try{

            Constructor constructor = getClazz().getConstructor(Main.class, Plague.class, ConfigurationSection.class);

            return (PlagueCause) constructor.newInstance(plugin, plague, section);

        }
        catch(NoSuchMethodException |
                InstantiationException | IllegalAccessException e){

            throw new PlagueFailedToLoadException("Could not instantiate cause \"" + toString() +
                    "\" due to the following exception: " + ExceptionUtils.getStackTrace(e));

        }
        catch(InvocationTargetException e){

            if(e.getCause() instanceof PlagueFailedToLoadException)
                throw new PlagueFailedToLoadException(e.getCause().getMessage());

            throw new PlagueFailedToLoadException("Could not instantiate attribute \"" + toString() +
                    "\" due to the following exception: " + ExceptionUtils.getStackTrace(e.getCause()));

        }

    }

    public static CauseType getCauseType(String key){

        for(CauseType ct : CauseType.values())
            if(ct.getKey().equals(key))
                return ct;

        return null;

    }

    public static Set<PlagueCause> getCauses(Main plugin, Plague plague, ConfigurationSection section){

        Set<PlagueCause> causes = new HashSet<>();

        for(String s : section.getKeys(false)){

            CauseType ct = CauseType.getCauseType(s);

            if(ct == null)
                throw new PlagueFailedToLoadException("Cause \"" + s + "\" is invalid.");

            causes.add(ct.create(plugin, plague, section.getConfigurationSection(s)));

        }

        return causes;

    }

}

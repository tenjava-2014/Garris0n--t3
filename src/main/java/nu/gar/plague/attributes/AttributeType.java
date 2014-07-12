package nu.gar.plague.attributes;

import nu.gar.plague.Main;
import nu.gar.plague.attributes.types.AttributePotionEffect;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public enum AttributeType{

    POTION_EFFECT("potion", AttributePotionEffect.class);

    private String key;
    private Class<? extends PlagueAttribute> clazz;

    private AttributeType(String key, Class<? extends PlagueAttribute> clazz){

        this.key = key;
        this.clazz = clazz;

    }

    public String getKey(){

        return key;

    }

    public Class<? extends PlagueAttribute> getClazz(){

        return clazz;

    }

    public PlagueAttribute create(Main plugin, ConfigurationSection section){

        try{

            Constructor constructor = getClazz().getConstructor(Main.class, ConfigurationSection.class);

            return (PlagueAttribute) constructor.newInstance(plugin, section);

        }
        catch(NoSuchMethodException |
                InstantiationException | IllegalAccessException e){

            throw new PlagueFailedToLoadException("Could not instantiate attribute \"" + toString() +
                    "\" due to the following exception: " + ExceptionUtils.getStackTrace(e));

        }
        catch(InvocationTargetException e){

            if(e.getCause() instanceof PlagueFailedToLoadException)
                throw new PlagueFailedToLoadException(e.getCause().getMessage());

            throw new PlagueFailedToLoadException("Could not instantiate attribute \"" + toString() +
                    "\" due to the following exception: " + ExceptionUtils.getStackTrace(e.getCause()));

        }

    }

    public static AttributeType getAttributeType(String key){

        for(AttributeType at : AttributeType.values())
            if(at.getKey().equals(key))
                return at;

        return null;

    }

    public static Set<PlagueAttribute> getAttributes(Main plugin, ConfigurationSection section){

        Set<PlagueAttribute> attributes = new HashSet<>();

        for(String s : section.getKeys(false)){

            AttributeType at = AttributeType.getAttributeType(s);

            if(at == null)
                throw new PlagueFailedToLoadException("Attribute \"" + s + "\" is invalid.");

            attributes.add(at.create(plugin, section.getConfigurationSection(s)));

        }

        return attributes;

    }

}

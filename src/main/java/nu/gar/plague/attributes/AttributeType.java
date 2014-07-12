package nu.gar.plague.attributes;

import nu.gar.plague.Main;
import nu.gar.plague.attributes.types.AttributePoison;
import org.bukkit.configuration.ConfigurationSection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public enum AttributeType{

    POISON(AttributePoison.class);

    private Class<? extends PlagueAttribute> clazz;

    AttributeType(Class<? extends PlagueAttribute> clazz){

        this.clazz = clazz;

    }

    public Class<? extends PlagueAttribute> getClazz(){

        return clazz;

    }

    public PlagueAttribute create(Main plugin, ConfigurationSection section){

        try{

            Constructor constructor = getClazz().getConstructor(Main.class, ConfigurationSection.class);

            return (PlagueAttribute) constructor.newInstance(plugin, section);

        }
        catch(NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException e){

            //TODO: Handle
            e.printStackTrace();
            return null;

        }

    }

    public static Set<PlagueAttribute> getAttributes(Main plugin, ConfigurationSection section){

        Set<PlagueAttribute> attributes = new HashSet<>();

        for(String s : section.getKeys(false)){

            AttributeType at = AttributeType.valueOf(s);

            if(at == null)
                continue; //TODO: handle

            attributes.add(at.create(plugin, section));

        }

        return attributes;

    }

}

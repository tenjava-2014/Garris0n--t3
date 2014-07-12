package nu.gar.plague.causes;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import nu.gar.plague.causes.types.CauseRandom;
import org.bukkit.configuration.ConfigurationSection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public enum CauseType{

    RANDOM(CauseRandom.class);

    private Class<? extends PlagueCause> clazz;

    CauseType(Class<? extends PlagueCause> clazz){

        this.clazz = clazz;

    }

    public Class<? extends PlagueCause> getClazz(){

        return clazz;

    }

    public PlagueCause create(Main plugin, Plague plague, ConfigurationSection section){

        try{

            Constructor constructor = getClazz().getConstructor(Main.class, Plague.class, ConfigurationSection.class);

            return (PlagueCause) constructor.newInstance(plugin, plague, section);

        }
        catch(NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException e){

            //TODO: Handle
            e.printStackTrace();
            return null;

        }

    }

    public static Set<PlagueCause> getCauses(Main plugin, Plague plague, ConfigurationSection section){

        Set<PlagueCause> causes = new HashSet<>();

        for(String s : section.getKeys(false)){

            CauseType ct = CauseType.valueOf(s);

            if(ct == null)
                continue; //TODO: handle

            causes.add(ct.create(plugin, plague, section.getConfigurationSection(s)));

        }

        return causes;

    }

}

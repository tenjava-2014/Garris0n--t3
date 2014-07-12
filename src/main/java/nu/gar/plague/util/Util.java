package nu.gar.plague.util;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

public class Util{

    public static List<Entity> getEntities(Set<EntityType> types, Set<World> worlds){

        Iterator<EntityType> typeIterator = types.iterator();

        Class[] classes = new Class[types.size()];

        for(int i = 0; i < types.size(); i++)
            classes[i] = typeIterator.next().getEntityClass();

        List<Entity> entities = new ArrayList<>();

        for(World w : worlds)
            entities.addAll(w.getEntitiesByClasses(classes));

        return entities;

    }

}

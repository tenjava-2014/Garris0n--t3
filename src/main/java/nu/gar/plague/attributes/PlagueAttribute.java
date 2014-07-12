package nu.gar.plague.attributes;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;

public abstract class PlagueAttribute{

    private List<Class<? extends Entity>> vulnerable;

    public PlagueAttribute(Class<? extends Entity>... vulnerable){

        this(Arrays.asList(vulnerable));

    }

    public PlagueAttribute(List<Class<? extends Entity>> vulnerable){

        this.vulnerable = vulnerable;

    }

    public List<Class<? extends Entity>> getVulnerableEntities(){

        return vulnerable;

    }

    public boolean isVulnerable(Class<? extends Entity> clazz){

        return getVulnerableEntities().contains(clazz);

    }

    public boolean isVulnerable(Entity entity){

        return isVulnerable(entity.getClass());

    }

    public abstract void giveSymptoms(Entity entity);

}

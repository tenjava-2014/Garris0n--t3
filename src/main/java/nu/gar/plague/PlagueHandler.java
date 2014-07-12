package nu.gar.plague;

import java.util.HashMap;
import java.util.Map;

public class PlagueHandler{

    private Main plugin;

    private Map<String, Plague> plagues;

    public PlagueHandler(Main plugin){

        this.plugin = plugin;
        this.plagues = new HashMap<>();

    }

    public void addPlague(String name, Plague plague){

        getPlagues().put(name, plague);

    }

    public Map<String, Plague> getPlagues(){

        return plagues;

    }

}

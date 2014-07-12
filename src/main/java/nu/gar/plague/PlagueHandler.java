package nu.gar.plague;

import java.util.Map;

public class PlagueHandler{

    private Main plugin;

    private Map<String, Plague> plagues;

    public PlagueHandler(Main plugin, Map<String, Plague> plagues){

        this.plugin = plugin;
        this.plagues = plagues;

    }

}

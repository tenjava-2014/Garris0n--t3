package nu.gar.plague;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class PlagueHandler{

    private Main plugin;

    private Map<String, Plague> plagues;

    public PlagueHandler(Main plugin){

        this.plugin = plugin;
        this.plagues = new HashMap<>();

    }

    private void loadPlagues(){

        FileConfiguration plagues = plugin.getPlagues();

        for(String s : plagues.getKeys(false)){

            addPlague(s, new Plague(plugin, plagues.getConfigurationSection(s)));
            //TODO: Error checking

        }

    }

    public void reloadPlagues(){

        plagues.clear();
        loadPlagues();

    }

    public void addPlague(String name, Plague plague){

        getPlagues().put(name, plague);

    }

    public Map<String, Plague> getPlagues(){

        return plagues;

    }

}

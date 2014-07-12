package nu.gar.plague;

import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class PlagueHandler{

    private Main plugin;

    private Map<String, Plague> plagues;

    public PlagueHandler(Main plugin){

        this.plugin = plugin;
        this.plagues = new HashMap<>();

        loadPlagues();

    }

    private void loadPlagues(){

        FileConfiguration plagues = plugin.getPlagues();

        for(String s : plagues.getKeys(false)){

            try{

                Plague p = new Plague(plugin, plagues.getConfigurationSection(s));
                addPlague(s, p);

                plugin.getLogger().info("Loaded Plague \"" + p.getDisplayName() + "\" (" + s + ").");

            }
            catch(PlagueFailedToLoadException e){

                plugin.getLogger().warning("Plague \"" + s + "\" failed to load: " + e.getMessage());

            }

        }

    }

    public void reloadPlagues(){

        plagues.clear();
        loadPlagues();

    }

    public void stopAll(){

        for(Plague p : getPlagues().values())
            p.stop();

    }

    public void addPlague(String name, Plague plague){

        getPlagues().put(name, plague);

    }

    public Map<String, Plague> getPlagues(){

        return plagues;

    }

}

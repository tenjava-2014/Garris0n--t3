package nu.gar.plague.causes;

import nu.gar.plague.Main;
import nu.gar.plague.Plague;
import org.bukkit.event.Listener;

public abstract class PlagueCause implements Listener{

    private Main plugin;
    private Plague plague;

    public PlagueCause(Main plugin, Plague plague){

        this.plugin = plugin;
        this.plague = plague;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    public Main getPlugin(){

        return plugin;

    }

}

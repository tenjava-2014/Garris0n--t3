package nu.gar.plague;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin{

    private final boolean debug = false;

    private File plaguesFile;
    private FileConfiguration plagues;

    private PlagueHandler plagueHandler;

    @Override
    public void onEnable(){

        saveDefaultPlagues();

        this.plagueHandler = new PlagueHandler(this);

    }

    @Override
    public void onDisable(){

        getPlagueHandler().stopAll();

    }

    public PlagueHandler getPlagueHandler(){

        return plagueHandler;

    }

    public void debug(String message){

        if(debug)
            Bukkit.getServer().broadcastMessage("[Debug]: " + message);

    }

    public void saveDefaultPlagues(){

        if(plaguesFile == null)
            plaguesFile = new File(getDataFolder(), "plagues.yml");

        if(!plaguesFile.exists())
            saveResource("plagues.yml", false);

    }

    public void reloadPlagues(){

        if(plaguesFile == null || !plaguesFile.exists())
            saveDefaultPlagues();

        plagues = YamlConfiguration.loadConfiguration(plaguesFile);

    }

    public FileConfiguration getPlagues(){

        if(plagues == null)
            this.reloadPlagues();

        return plagues;

    }

    public void savePlagues(){

        if(plagues == null || plaguesFile == null)
            return;

        try{

            getPlagues().save(plaguesFile);

        }
        catch(IOException ex){

            this.getLogger().log(Level.SEVERE, "Could not save config to " + plaguesFile, ex);

        }

    }


}

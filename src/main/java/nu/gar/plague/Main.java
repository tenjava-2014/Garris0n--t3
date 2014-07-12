package nu.gar.plague;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin{

    private PlagueHandler plagueHandler;

    @Override
    public void onEnable(){

        this.plagueHandler = new PlagueHandler(this);

        this.plagueHandler.addPlague("test", new Plague(this));

    }

    @Override
    public void onDisable(){

        //TODO: Disable stuff?

    }

    public PlagueHandler getPlagueHandler(){

        return plagueHandler;

    }

}

package nu.gar.plague.attributes.types;

import nu.gar.plague.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AttributeBroadcastMessage extends AttributeSendMessage{


    public AttributeBroadcastMessage(Main plugin, ConfigurationSection section){

        super(plugin, section);

    }

    @Override
    public void giveSymptoms(LivingEntity entity){

        if(entity instanceof Player)
            Bukkit.getServer().broadcastMessage(getMessage((Player) entity));

    }

    @Override
    public void stop(){}

}

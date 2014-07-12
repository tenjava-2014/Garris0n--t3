package nu.gar.plague.attributes.types;

import nu.gar.plague.Main;
import nu.gar.plague.attributes.PlagueAttribute;
import nu.gar.plague.exceptions.PlagueFailedToLoadException;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AttributeSendMessage extends PlagueAttribute{

    public static final String NAME_REPLACEMENT = "%player-name%";
    public static final String DISPLAY_NAME_REPLACEMENT = "%player-display-name%";

    private String message;

    public AttributeSendMessage(Main plugin, ConfigurationSection section){

        super(plugin);

        for(Key k : Key.values())
            if(!section.contains(k.getString()))
                throw new PlagueFailedToLoadException("Attribute \"" + section.getName() +
                        "\" is  missing a required option: " + k + ".");

        this.message = ChatColor.translateAlternateColorCodes('&',
                section.getString(Key.MESSAGE.getString()));

    }

    public String getMessage(){

        return message;

    }

    public String getMessage(Player player){

        return getMessage().replace(NAME_REPLACEMENT, player.getName())
                .replace(DISPLAY_NAME_REPLACEMENT, player.getDisplayName());

    }

    @Override
    public void giveSymptoms(LivingEntity entity){

        if(entity instanceof Player)
            ((Player) entity).sendMessage(getMessage((Player) entity));

    }

    @Override
    public void stop(){}

    private static enum Key{

        MESSAGE("message");

        private String string;

        private Key(String string){

            this.string = string;

        }

        public String getString(){

            return string;

        }

    }

}

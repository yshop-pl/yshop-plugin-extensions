package pl.yshop.plugin.extension.shopgui.pipes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.yshop.plugin.api.commands.PlatformSender;

public class PlatformSenderToPlayerPipe {
    public static Player make(PlatformSender platformSender) {
        if (platformSender.getUniqueId() == null) return null;
        return Bukkit.getPlayer(platformSender.getUniqueId());
    }
}

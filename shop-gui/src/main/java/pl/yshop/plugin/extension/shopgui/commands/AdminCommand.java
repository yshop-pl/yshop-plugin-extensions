package pl.yshop.plugin.extension.shopgui.commands;

import pl.yshop.plugin.api.commands.PlatformCommand;
import pl.yshop.plugin.api.commands.PlatformSender;
import pl.yshop.plugin.api.commands.annotations.Command;
import pl.yshop.plugin.api.commands.annotations.Execute;
import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;

@Command(name = "itemshop-reload")
public class AdminCommand implements PlatformCommand {
    @Execute
    void execute(PlatformSender sender) {
        try {
            ShopGuiExtension.config.load(true);
            sender.sendMessage("Przeladowano!");
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage("Wystapil blad: " + e.getMessage());
        }
    }
}
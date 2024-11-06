package pl.yshop.plugin.extension.shopgui.commands;


import pl.yshop.plugin.api.commands.PlatformCommand;
import pl.yshop.plugin.api.commands.PlatformSender;
import pl.yshop.plugin.api.commands.annotations.Command;
import pl.yshop.plugin.api.commands.annotations.Execute;
import pl.yshop.plugin.extension.shopgui.views.CategoriesView;

@Command(name = "itemshop")
public class ShopCommand implements PlatformCommand {
    @Execute
    void execute(PlatformSender sender) {
        new CategoriesView().sender(sender).open();
    }
}

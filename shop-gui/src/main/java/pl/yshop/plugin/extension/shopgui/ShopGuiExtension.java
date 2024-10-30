package pl.yshop.plugin.extension.shopgui;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pl.yshop.plugin.api.Extension;
import pl.yshop.plugin.extension.shopgui.commands.ShopCommand;
import pl.yshop.plugin.extension.shopgui.configuration.ExtensionConfiguration;
import xyz.xenondevs.invui.InvUI;

import java.io.File;

public class ShopGuiExtension extends Extension {
    public static ExtensionConfiguration config;
    @Override
    public void onEnable() {
        Plugin plugin = (Plugin) this.platform.plugin();
        InvUI.getInstance().setPlugin(plugin);

        this.platform.registerCommand(new ShopCommand());

        config = ConfigManager.create(ExtensionConfiguration.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            it.withBindFile(new File(plugin.getDataFolder().getPath() + "/extensions/ShopGUI/config.yml"));
            it.saveDefaults();
            it.load(true);
        });

        new ShopService(this.platform);
    }

    @Override
    public void onDisable() {

    }
}

package pl.yshop.plugin.extension.shopgui;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.placeholders.Placeholders;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pl.yshop.plugin.api.Extension;
import pl.yshop.plugin.extension.shopgui.commands.AdminCommand;
import pl.yshop.plugin.extension.shopgui.commands.ShopCommand;
import pl.yshop.plugin.extension.shopgui.configuration.ExtensionConfiguration;
import pl.yshop.plugin.extension.shopgui.requests.entities.Category;
import pl.yshop.plugin.extension.shopgui.requests.entities.Product;
import xyz.xenondevs.invui.InvUI;

import java.io.File;

public class ShopGuiExtension extends Extension {
    public static ExtensionConfiguration config;
    public static Plugin plugin;
    public static final Placeholders placeholders = Placeholders.create();
    public static BukkitAudiences audiences;
    @Override
    public void onEnable() {
        plugin = (Plugin) this.platform.plugin();
        InvUI.getInstance().setPlugin(plugin);

        audiences = BukkitAudiences.create(plugin);

        this.platform.registerCommand(new ShopCommand());
        this.platform.registerCommand(new AdminCommand());

        // Category
        placeholders.registerPlaceholder(Category.class, "name", (e, a, o) -> e.getName());

        // Product
        placeholders.registerPlaceholder(Product.class, "name", (e, a, o) -> e.getName());
        placeholders.registerPlaceholder(Product.class, "short_description", (e, a, o) -> e.getShort_description());
        placeholders.registerPlaceholder(Product.class, "purchases", (e, a, o) -> e.getPurchases());
        placeholders.registerPlaceholder(Product.class, "promoted", (e, a, o) -> e.isPromoted());
        placeholders.registerPlaceholder(Product.class, "promotionPercentage", (e, a, o) -> e.getPromotionPercentage());


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

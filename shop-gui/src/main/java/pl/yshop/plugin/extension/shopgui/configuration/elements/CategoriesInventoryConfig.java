package pl.yshop.plugin.extension.shopgui.configuration.elements;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.yshop.plugin.extension.shopgui.views.ViewConfig;

import java.util.HashMap;
import java.util.List;

public class CategoriesInventoryConfig extends OkaeriConfig implements ViewConfig {
    public String title = "<bold>Wybierz kategorie</bold>";
    public List<String> schema = List.of(
            "# # # # # # # # #",
            "# x x x x x x x #",
            "# x x x x x x x #",
            "# # # # # # # # #"
    );

    public HashMap<Character, ItemStack> items = new HashMap<Character, ItemStack>() {{
        put('#', new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
    }};

    @CustomKey("category_item")
    public ItemStack categoryItem = this.getCategoryItem();

    private ItemStack getCategoryItem() {
        ItemStack itemStack = new ItemStack(Material.BEDROCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(MiniMessage.miniMessage().serialize(MiniMessage.miniMessage().deserialize("<b><red>RANGI</red>")));
        itemMeta.setLore(List.of("", "Kliknij aby wybrać", ""));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public List<String> schema() {
        return this.schema;
    }

    @Override
    public HashMap<Character, ItemStack> items() {
        return this.items;
    }
}

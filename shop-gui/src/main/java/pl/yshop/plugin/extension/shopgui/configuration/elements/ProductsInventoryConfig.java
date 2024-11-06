package pl.yshop.plugin.extension.shopgui.configuration.elements;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.yshop.plugin.extension.shopgui.views.ViewConfig;

import java.util.HashMap;
import java.util.List;

public class ProductsInventoryConfig extends OkaeriConfig implements ViewConfig {
    public String title = "Wybierz produkt";
    public List<String> schema = List.of(
            "# # # # # # # # #",
            "# x x x x x x x #",
            "# x x x x x x x #",
            "# # # < # > # # #"
    );

    public HashMap<Character, ItemStack> items = new HashMap<Character, ItemStack>() {{
        put('#', new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
    }};

    @CustomKey("product_item")
    public ItemStack productItem = this.getProductItem();

    private ItemStack getProductItem() {
        ItemStack itemStack = new ItemStack(Material.BEDROCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("{product.name}");
        itemMeta.setLore(List.of(
                "",
                "{Produkt jest na promocji,Produkt nie jest na promocji#product.promoted}",
                "{product.short_description}",
                "Produkt został zakupiony {product.purchases} razy",
                ""
        ));
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

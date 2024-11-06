package pl.yshop.plugin.extension.shopgui.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Exclude;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.yshop.plugin.extension.shopgui.configuration.elements.CategoriesInventoryConfig;
import pl.yshop.plugin.extension.shopgui.configuration.elements.ProductsInventoryConfig;

import java.util.List;

public class ExtensionConfiguration extends OkaeriConfig {
    @CustomKey("categories_inventory")
    public CategoriesInventoryConfig categories = new CategoriesInventoryConfig();

    @CustomKey("products_inventory")
    public ProductsInventoryConfig products = new ProductsInventoryConfig();

    @CustomKey("previous_page_button")
    public ItemStack previousPageButton = this.getPreviousPageButton();

    @CustomKey("next_page_button")
    public ItemStack nextPageButton = this.getNextPageButton();

    private ItemStack getPreviousPageButton() {
        ItemStack itemStack = new ItemStack(Material.RED_CONCRETE);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("<red>Poprzednia strona");
        meta.setLore(List.of("", "Kliknij aby przejsc do poprzedniej strony", ""));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    private ItemStack getNextPageButton() {
        ItemStack itemStack = new ItemStack(Material.GREEN_CONCRETE);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("<red>Następna strona");
        meta.setLore(List.of("", "Kliknij aby przejsc do nastepnej strony", ""));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

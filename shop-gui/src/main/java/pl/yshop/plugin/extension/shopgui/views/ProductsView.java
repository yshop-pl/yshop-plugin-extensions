package pl.yshop.plugin.extension.shopgui.views;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;
import pl.yshop.plugin.extension.shopgui.configuration.elements.ProductsInventoryConfig;
import pl.yshop.plugin.extension.shopgui.utils.MessageUtils;
import pl.yshop.plugin.extension.shopgui.views.components.BackItem;
import pl.yshop.plugin.extension.shopgui.views.components.ForwardItem;
import pl.yshop.plugin.extension.shopgui.requests.entities.Category;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductsView extends View {
    private final ProductsInventoryConfig config = ShopGuiExtension.config.products;
    private Category category;

    protected ProductsView() {
        super(ShopGuiExtension.config.products);
    }

    public ProductsView category(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public List<Item> content() {
        return this.category.getProducts().stream()
                .map(product -> new AbstractItem() {
                    @Override
                    public ItemProvider getItemProvider() {
                        return new ItemBuilder(MessageUtils.colorizeItemStack(config.productItem, new HashMap<>(){{
                            put("product", product);
                        }}));
                    }
                    @Override
                    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {

                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<Character, Item> ingredients() {
        return Map.of(
                '<', new BackItem(),
                '>', new ForwardItem()
        );
    }

    @Override
    public Runnable closeHandler() {
        return () -> Bukkit.getScheduler().runTask(
                ShopGuiExtension.plugin,
                () -> new CategoriesView().sender(this.sender).open()
        );
    }
}

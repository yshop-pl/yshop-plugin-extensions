package pl.yshop.plugin.extension.shopgui.views;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;
import pl.yshop.plugin.extension.shopgui.ShopService;
import pl.yshop.plugin.extension.shopgui.configuration.elements.CategoriesInventoryConfig;
import pl.yshop.plugin.extension.shopgui.utils.MessageUtils;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoriesView extends View {
    private final ShopService shopService = ShopService.getInstance();
    private final CategoriesInventoryConfig config = ShopGuiExtension.config.categories;

    public CategoriesView() {
        super(ShopGuiExtension.config.categories);
    }

    @Override
    public List<Item> content() {
        return this.shopService.getServer().categories.stream()
                .filter(it -> !it.isHidden())
                .map(category -> new AbstractItem() {
                    @Override
                    public ItemProvider getItemProvider() {
                        return new ItemBuilder(MessageUtils.colorizeItemStack(config.categoryItem, new HashMap<>(){{
                            put("category", category);
                        }}));
                    }

                    @Override
                    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
                        new ProductsView().category(category).sender(sender).open();
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<Character, Item> ingredients() {
        return Map.of();
    }

    @Override
    public Runnable closeHandler() {
        return null;
    }
}

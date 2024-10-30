package pl.yshop.plugin.extension.shopgui.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import pl.yshop.plugin.api.commands.PlatformSender;
import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;
import pl.yshop.plugin.extension.shopgui.ShopService;
import pl.yshop.plugin.extension.shopgui.configuration.ExtensionConfiguration;
import pl.yshop.plugin.extension.shopgui.configuration.elements.CategoriesInventoryConfig;
import pl.yshop.plugin.extension.shopgui.pipes.PlatformSenderToPlayerPipe;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesInventory {
    private final ShopService shopService = ShopService.getInstance();
    private final CategoriesInventoryConfig config = ShopGuiExtension.config.categories;

    public void open(PlatformSender sender) {
        Player player = PlatformSenderToPlayerPipe.make(sender);
        if (player == null) return;
        Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));

        List<Item> items = this.shopService.getServer().categories.stream()
                .filter(it -> !it.isHidden())
                .map(category -> new AbstractItem() {
                    @Override
                    public ItemProvider getItemProvider() {
                        return new ItemBuilder(Material.STONE).setDisplayName(category.getName());
                    }

                    @Override
                    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
                        new ProductsInventory().open(sender, category);
                    }
                })
                .collect(Collectors.toList());

        Gui gui = PagedGui.items()
                .setStructure(this.config.schema.toArray(new String[0]))
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('#', border)
                .setContent(items)
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setGui(gui)
                .setTitle(this.config.title)
                .build();
        window.open();
    }
}

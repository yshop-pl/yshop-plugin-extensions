package pl.yshop.plugin.extension.shopgui.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.yshop.plugin.api.commands.PlatformSender;
import pl.yshop.plugin.extension.shopgui.ShopService;
import pl.yshop.plugin.extension.shopgui.inventory.components.BackItem;
import pl.yshop.plugin.extension.shopgui.inventory.components.ForwardItem;
import pl.yshop.plugin.extension.shopgui.pipes.PlatformSenderToPlayerPipe;
import pl.yshop.plugin.extension.shopgui.requests.entities.Category;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsInventory {
    private final ShopService shopService = ShopService.getInstance();

    public void open(PlatformSender sender, Category category) {
        Player player = PlatformSenderToPlayerPipe.make(sender);
        if (player == null) return;
        Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));

        List<Item> items = category.getProducts().stream()
                .map(product -> new SimpleItem(new ItemBuilder(Material.STONE)
                        .setDisplayName(product.getName())
                        .addLoreLines(product.isPromoted() ? "Produkt na promocji -"+product.getPromotionPercentage()+"%" : "")
                        .addLoreLines(product.getShort_description(), "Produkt został zakupiony: " + product.getPurchases(), "")
                ))
                .collect(Collectors.toList());

        Gui gui = PagedGui.items()
                .setStructure(
                        "# # # # # # # # #",
                        "# x x x x x x x #",
                        "# x x x x x x x #",
                        "# # # < # > # # #"
                )
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('#', border)
                .addIngredient('<', new BackItem())
                .addIngredient('>', new ForwardItem())
                .setContent(items)
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setGui(gui)
                .setTitle("Wybierz produkt")
                .build();
        window.open();
    }
}

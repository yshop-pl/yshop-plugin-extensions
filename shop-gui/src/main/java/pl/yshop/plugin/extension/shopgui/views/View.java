package pl.yshop.plugin.extension.shopgui.views;

import org.bukkit.entity.Player;
import pl.yshop.plugin.api.commands.PlatformSender;
import pl.yshop.plugin.extension.shopgui.pipes.PlatformSenderToPlayerPipe;
import pl.yshop.plugin.extension.shopgui.utils.MessageUtils;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.List;
import java.util.Map;

public abstract class View {
    private final ViewConfig config;
    public PlatformSender sender;

    protected View(ViewConfig viewConfig) {
        this.config = viewConfig;
    }

    public View sender(PlatformSender sender) {
        this.sender = sender;
        return this;
    }

    public abstract List<Item> content();
    public abstract Map<Character, Item> ingredients();
    public abstract Runnable closeHandler();

    public void open() {
        Player player = PlatformSenderToPlayerPipe.make(this.sender);
        if (player == null) return;

        var builder = PagedGui.items()
                .setStructure(this.config.schema().toArray(new String[0]))
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL);

        this.ingredients().forEach(builder::addIngredient);

        this.config.items().forEach((key, value) -> {
            builder.addIngredient(key, new SimpleItem(new ItemBuilder(MessageUtils.colorizeItemStack(value))));
        });
        if (this.content() != null) builder.setContent(this.content());

        var window = Window.single()
                .setViewer(player)
                .setGui(builder.build())
                .setTitle(MessageUtils.format(this.config.title()));
        if (this.closeHandler() != null)
            window.addCloseHandler(this.closeHandler());
        window.build().open();
    }
//    public void open(PlatformSender sender) {
//        Player player = PlatformSenderToPlayerPipe.make(sender);
//        if (player == null) return;
//
//        List<Item> items = this.shopService.getServer().categories.stream()
//                .filter(it -> !it.isHidden())
//                .map(category -> new AbstractItem() {
//                    @Override
//                    public ItemProvider getItemProvider() {
//                        return new ItemBuilder(MessageUtils.colorizeItemStack(config.categoryItem, new HashMap<>(){{
//                            put("category", category);
//                        }}));
//                    }
//
//                    @Override
//                    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
//                        new ProductsInventory().open(sender, category);
//                    }
//                })
//                .collect(Collectors.toList());
//
//        var builder = PagedGui.items()
//                .setStructure(this.config.schema.toArray(new String[0]))
//                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL);
//
//        this.config.items.forEach((key, value) -> {
//            builder.addIngredient(key, new SimpleItem(new ItemBuilder(MessageUtils.colorizeItemStack(value))));
//        });
//
//        Window window = Window.single()
//                .setViewer(player)
//                .setGui(builder.setContent(items).build())
//                .setTitle(MessageUtils.format(this.config.title))
//                .build();
//        window.open();
//    }
}

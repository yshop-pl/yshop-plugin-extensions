package pl.yshop.plugin.extension.shopgui.inventory.components;

import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

public class BackItem extends PageItem {

    public BackItem() {
        super(false);
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        return new ItemBuilder(ShopGuiExtension.config.previousPageButton);
    }
}
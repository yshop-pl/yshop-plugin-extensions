package pl.yshop.plugin.extension.shopgui.views.components;

import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;
import pl.yshop.plugin.extension.shopgui.utils.MessageUtils;
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
        return new ItemBuilder(MessageUtils.colorizeItemStack(ShopGuiExtension.config.previousPageButton));
    }
}
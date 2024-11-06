package pl.yshop.plugin.extension.shopgui.views;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public interface ViewConfig {
    String title();
    List<String> schema();
    HashMap<Character, ItemStack> items();
}

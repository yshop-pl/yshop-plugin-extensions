package pl.yshop.plugin.extension.shopgui.utils;

import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.yshop.plugin.extension.shopgui.ShopGuiExtension;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MessageUtils {
    public static String format(String message) {
        message = message.replace("§", "&");
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(message);
        return implementLegacyColors(LegacyComponentSerializer.legacySection().serialize(parsed));
    }
    public static List<String> format(List<String> messages) {
        if (messages == null) return null;
        return messages.stream().map(MessageUtils::format).toList();
    }
    public static String implementLegacyColors(String message) {
        if (message == null) return null;
        return ChatColor.translateAlternateColorCodes('&', message.replace("<<", "«").replace(">>", "»"));
    }
    public static ItemStack colorizeItemStack(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(format(meta.getDisplayName()));
        meta.setLore(format(meta.getLore()));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack colorizeItemStack(ItemStack itemStack, Map<String, Object> values) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.hasDisplayName()) meta.setDisplayName(formatPlaceholders(meta.getDisplayName(), values));
        if (meta.hasLore()) meta.setLore(meta.getLore().stream().map(it -> formatPlaceholders(it, values)).toList());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static String formatPlaceholders(String message,  Map<String, Object> values) {
        CompiledMessage name = CompiledMessage.of(message);
        PlaceholderContext context = ShopGuiExtension.placeholders.contextOf(name);
        values.forEach(context::with);
        return format(context.apply());
    }
}

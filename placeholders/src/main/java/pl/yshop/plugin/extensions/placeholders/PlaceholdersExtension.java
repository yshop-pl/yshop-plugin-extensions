package pl.yshop.plugin.extensions.placeholders;

import pl.yshop.plugin.api.Extension;

public class PlaceholdersExtension extends Extension {

    @Override
    public void onEnable() {
        new Expansion().register();
    }

    @Override
    public void onDisable() {

    }
}
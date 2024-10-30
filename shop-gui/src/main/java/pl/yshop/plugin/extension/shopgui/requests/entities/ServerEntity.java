package pl.yshop.plugin.extension.shopgui.requests.entities;

import java.util.List;

public class ServerEntity {
    private int id;
    private String name;
    private String slug;
    public boolean hidden;
    public List<Category> categories;

    public int getId() {
        return id;
    }

    public boolean isHidden() {
        return hidden;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }
}

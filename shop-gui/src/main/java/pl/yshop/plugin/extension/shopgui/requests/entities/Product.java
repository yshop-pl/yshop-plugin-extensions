package pl.yshop.plugin.extension.shopgui.requests.entities;

public class Product {
    private int id;
    private String name;
    private String short_description;
    private String long_description;
    private boolean require_online;
    private int order;
    private String imageUrl;
    private boolean promoted;
    private int promotionPercentage;
    private int purchases;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public boolean isRequire_online() {
        return require_online;
    }

    public int getPurchases() {
        return purchases;
    }

    public int getPromotionPercentage() {
        return promotionPercentage;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getOrder() {
        return order;
    }
}

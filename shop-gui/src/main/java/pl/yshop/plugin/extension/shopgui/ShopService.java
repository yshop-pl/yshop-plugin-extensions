package pl.yshop.plugin.extension.shopgui;

import pl.yshop.plugin.api.Platform;
import pl.yshop.plugin.api.request.RequestException;
import pl.yshop.plugin.extension.shopgui.requests.GetServerRequest;
import pl.yshop.plugin.extension.shopgui.requests.entities.ServerEntity;

import java.util.List;

public class ShopService {
    public static ShopService instance;
    private final Platform platform;
    private ServerEntity server;

    public ShopService(Platform platform) {
        instance = this;
        this.platform = platform;
        this.fetchServer();
    }

    public static ShopService getInstance() {
        return instance;
    }

    private void fetchServer() {
        try {
            this.server = this.platform.getRequester().make(new GetServerRequest());
        } catch (RequestException e) {
            this.platform.logger().error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ServerEntity getServer() {
        return server;
    }
}

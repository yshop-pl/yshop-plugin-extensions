package pl.yshop.plugin.extension.shopgui.requests;

import pl.yshop.plugin.api.request.BaseRequest;
import pl.yshop.plugin.extension.shopgui.requests.entities.ServerEntity;

public class GetServerRequest implements BaseRequest<ServerEntity> {
    @Override
    public String path() {
        return "/server/{serverId}";
    }

    @Override
    public String method() {
        return "GET";
    }

    @Override
    public okhttp3.RequestBody requestBody() {
        return null;
    }

    @Override
    public Class<ServerEntity> responseClass() {
        return ServerEntity.class;
    }
}

package org.wceng.launcher;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.wceng.GptClient;
import org.wceng.component.GptChatStack;
import org.wceng.model.GptMessage;
import org.wceng.model.GptModelType;
import org.wceng.model.GptSession;
import org.wceng.request.GptRequest;
import org.wceng.response.GptResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class GptLauncher implements Callback {

    private final OkHttpClient httpClient;
    private final List<Call> pendingCall;
    private final Builder builder;
    private boolean isActive = false;

    public GptLauncher(Builder builder) {
        this.builder = builder;
        httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(builder.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(builder.readTimeout, TimeUnit.MILLISECONDS)
                .build();
        pendingCall = new ArrayList<>();
    }

    public abstract GptModelType supportedModelType();

    public abstract GptSession currentSession();

    protected abstract GptRequest buildRequest();

    public abstract GptResponse handleResponse(ResponseBody body) throws IOException;


    public void sendMessage(GptMessage message) {
        GptChatStack chatStack = currentSession().getChatStack();
        chatStack.addMessage(message);

        GptRequest gptRequest = buildRequest();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(gptRequest.getRequestBody().toString(), mediaType);

        Request request = new Request.Builder()
                .url(gptRequest.getUrl())
                .method(gptRequest.getMethod(), requestBody)
                .addHeader("Authorization", "Bearer " + gptRequest.getRequestHeader().getAuthorization())
                .addHeader("Content-Type", gptRequest.getRequestHeader().getContentType())
                .build();
        execute(request);
        changeStatue(true);
    }


    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        //todo

        ResponseBody body = response.body();
        if (body != null) {
            GptResponse gptResponse = handleResponse(body);
            if (builder.responseCallback != null) {
                builder.responseCallback.onReceived(gptResponse);
            }
        } else {
            if (builder.responseCallback != null) {
                builder.responseCallback.onError(new Exception("received null body"));
            }
        }
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        if (builder.responseCallback != null) {
            pendingCall.remove(call);
            builder.responseCallback.onError(e);
        }
    }

    protected void execute(Request request) {
        Call call = httpClient.newCall(request);
        call.enqueue(this);
        pendingCall.add(call);
    }

    public void stop() {
        if (isActive()) {
            for (Call call : pendingCall) {
                call.cancel();
            }
            pendingCall.clear();
        }
    }

    protected void changeStatue(boolean isActive) {
        this.isActive = isActive;
        if (builder.responseCallback != null) {
            builder.responseCallback.onStatue(isActive);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public static class Builder<T extends Builder> {
        final GptClient gptClient;
         long connectTimeout = 60 * 1000;
         long readTimeout = 60 * 1000;
         GptResponseCallback responseCallback = null;

        public Builder(GptClient gptClient) {
            this.gptClient = gptClient;
        }

        public T setConnectTimeout(long timeout) {
            this.connectTimeout = timeout;
            return (T) this;
        }

        public T setReadTimeout(long timeout) {
            this.readTimeout = timeout;
            return (T) this;
        }

        public T setResponseCallback(GptResponseCallback callback) {
            this.responseCallback = callback;
            return (T) this;
        }

    }


}

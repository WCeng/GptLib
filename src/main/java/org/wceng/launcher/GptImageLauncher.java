package org.wceng.launcher;

import okhttp3.ResponseBody;
import org.wceng.GptClient;
import org.wceng.model.GptModel;
import org.wceng.model.GptModelType;
import org.wceng.model.GptSession;
import org.wceng.request.GptImageRequestBody;
import org.wceng.request.GptRequest;
import org.wceng.response.GptChoices;
import org.wceng.response.GptResponse;

import java.io.IOException;

public class GptImageLauncher extends GptLauncher {
    private Builder builder;

    public GptImageLauncher(Builder builder) {
        super(builder);
        this.builder = builder;
    }

    public static class Builder extends GptLauncher.Builder<Builder> {

        private String prompt;
        private int n;
        private GptModel model;
        private String size;

        public Builder(GptClient gptClient) {
            super(gptClient);
        }

        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder setN(int n) {
            this.n = n;
            return this;
        }

        public Builder setModel(GptModel model) {
            this.model = model;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public GptImageLauncher build() {
            return new GptImageLauncher(this);
        }

    }

    @Override
    public GptModelType supportedModelType() {
        return GptModelType.IMAGE_GENERATION;
    }

    @Override
    public GptSession currentSession() {
        return builder.gptClient.getGptSessionManager().currentSession();
    }

    @Override
    protected GptRequest buildRequest() {
        return new GptRequest(
                builder.model.getName(),
                builder.gptClient.getBaseUrl() + builder.model.getType().getEndpoint(),
                new GptRequest.GptRequestHeader(builder.gptClient.getKey(), builder.model.getType().getContentType()),
                new GptImageRequestBody(builder.prompt, builder.n, builder.model.getName(), builder.size)
        );
    }

    @Override
    public GptResponse handleResponse(ResponseBody body) throws IOException {
        return new GptResponse.GptResponseBuilder()
                .gptChoices(new GptChoices(new GptChoices.Choice(body.string())))
                .build();
    }
}

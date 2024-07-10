package org.wceng.request;

import org.wceng.request.body.GptEmbeddingRequestBody;

public class GptEmbeddingRequest extends GptRequest<GptEmbeddingRequest.Builder> {

    public GptEmbeddingRequest(Builder builder) {
        super(builder);
    }

    @Override
    public GptRequestBody getRequestBody() {
        return new GptEmbeddingRequestBody(builder.model, builder.input);
    }

    public static class Builder extends GptRequest.GptRequestBuilder<Builder> {
        String model;
        String input;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder input(String input) {
            this.input = input;
            return this;
        }

        public GptRequest<Builder> build() {
            return new GptEmbeddingRequest(this);
        }
    }
}

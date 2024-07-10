package org.wceng.request;

import org.wceng.request.body.GptImageGenerationRequestBody;

public class GptImageGenerationRequest extends GptRequest<GptImageGenerationRequest.Builder> {

    public GptImageGenerationRequest(Builder builder) {
        super(builder);
    }

    @Override
    public GptRequestBody getRequestBody() {
        return new GptImageGenerationRequestBody(
                builder.model, builder.prompt, builder.n, builder.size
        );
    }

    public static class Builder extends GptRequestBuilder<Builder> {
        String model;
        String prompt;
        String size;
        Integer n;

        public GptImageGenerationRequest.Builder model(String model) {
            this.model = model;
            return this;
        }

        public GptImageGenerationRequest.Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public GptImageGenerationRequest.Builder size(String size) {
            this.size = size;
            return this;
        }

        public GptImageGenerationRequest.Builder n(Integer n) {
            this.n = n;
            return this;
        }

        public GptImageGenerationRequest build() {
            return new GptImageGenerationRequest(this);
        }

    }
}

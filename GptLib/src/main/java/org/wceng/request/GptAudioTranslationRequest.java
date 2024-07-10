package org.wceng.request;

import java.io.File;

public class GptAudioTranslationRequest extends GptRequest<GptAudioTranslationRequest.Builder>{

    public GptAudioTranslationRequest(Builder builder) {
        super(builder);
    }

    @Override
    public GptRequestBody getRequestBody() {
        GptFormRequestBody gptFormRequestBody = new GptFormRequestBody();
        gptFormRequestBody.files.add(builder.file);
        gptFormRequestBody.map.put("model", builder.model);
        gptFormRequestBody.map.put("prompt", builder.prompt);
        gptFormRequestBody.map.put("temperature", builder.temperature == null ? null : builder.temperature.toString());
        return gptFormRequestBody;
    }

    public static class Builder extends GptRequestBuilder<GptAudioTranslationRequest.Builder> {
        String model;
        File file;
        String prompt;
        Float temperature;

        public GptAudioTranslationRequest.Builder model(String model) {
            this.model = model;
            return this;
        }

        public GptAudioTranslationRequest.Builder file(File file) {
            this.file = file;
            return this;
        }

        public GptAudioTranslationRequest.Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public GptAudioTranslationRequest.Builder temperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public GptRequest<Builder> build() {
            return new GptAudioTranslationRequest(this);
        }
    }
}

package org.wceng.request;

import java.io.File;

public class GptAudioTranscriptionRequest extends GptRequest<GptAudioTranscriptionRequest.Builder> {

    public GptAudioTranscriptionRequest(Builder builder) {
        super(builder);
    }

    @Override
    public GptRequestBody getRequestBody() {
        GptFormRequestBody gptFormRequestBody = new GptFormRequestBody();
        gptFormRequestBody.files.add(builder.file);

        gptFormRequestBody.map.put("model", builder.model);
        gptFormRequestBody.map.put("prompt", builder.prompt);
        gptFormRequestBody.map.put("language", builder.language);
        gptFormRequestBody.map.put("temperature", builder.temperature == null ? null : builder.temperature.toString());

        return gptFormRequestBody;
    }

    public static class Builder extends GptRequestBuilder<Builder> {
        String model;
        File file;
        String prompt;
        String language;
        Float temperature;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }

        public Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder temperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public GptAudioTranscriptionRequest build() {
            return new GptAudioTranscriptionRequest(this);
        }
    }
}

package org.wceng.request;

import org.wceng.request.body.GptAudioSpeechRequestBody;

public class GptAudioSpeechRequest extends GptRequest<GptAudioSpeechRequest.Builder> {

    public GptAudioSpeechRequest(Builder builder) {
        super(builder);
    }

    @Override
    public GptRequestBody getRequestBody() {
        return new GptAudioSpeechRequestBody(builder.model, builder.input, builder.voice,
                builder.responseFormat, builder.speed);
    }

    public static class Builder extends GptRequestBuilder<Builder> {
        String model;
        String input;
        String voice;
        String responseFormat;
        Float speed;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder input(String input) {
            this.input = input;
            return this;
        }

        public Builder voice(String voice) {
            this.voice = voice;
            return this;
        }

        public Builder responseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder speed(Float speed) {
            this.speed = speed;
            return this;
        }

        public GptAudioSpeechRequest build() {
            return new GptAudioSpeechRequest(this);
        }

    }
}

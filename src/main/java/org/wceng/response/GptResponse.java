package org.wceng.response;

import org.wceng.model.GptSession;

public class GptResponse {

    private final GptSession gptSession;
    private final Boolean stream;
    private final byte[] data;
    private final TokenUsage usage;
    private final StreamHandler streamHandler;
    private final GptChoices gptChoices;

    public GptResponse(GptResponseBuilder builder) {
        this.gptSession = builder.gptSession;
        this.stream = builder.stream;
        this.data = builder.data;
        this.usage = builder.usage;
        this.streamHandler = builder.streamHandler;
        this.gptChoices = builder.gptChoices;
    }


    public GptChoices getGptChoices() {
        return gptChoices;
    }

    public StreamHandler getStreamHandler() {
        return streamHandler;
    }

    public GptSession getGptSession() {
        return gptSession;
    }

    public boolean isStream() {
        return stream;
    }

    public byte[] getData() {
        return data;
    }

    public TokenUsage getUsage() {
        return usage;
    }

    public static class GptResponseBuilder {
        GptSession gptSession;
        Boolean stream;
        byte[] data;
        TokenUsage usage;
        StreamHandler streamHandler;
        GptChoices gptChoices;


        public GptResponseBuilder session(GptSession gptSession) {
            this.gptSession = gptSession;
            return this;
        }

        public GptResponseBuilder stream(boolean stream) {
            this.stream = stream;
            return this;
        }

        public GptResponseBuilder data(byte[] data) {
            this.data = data;
            return this;
        }


        public GptResponseBuilder usage(TokenUsage usage) {
            this.usage = usage;
            return this;
        }

        public GptResponseBuilder streamHandler(StreamHandler streamHandler) {
            this.streamHandler = streamHandler;
            return this;
        }

        public GptResponseBuilder gptChoices(GptChoices gptChoices) {
            this.gptChoices = gptChoices;
            return this;
        }

        public GptResponse build() {
            return new GptResponse(this);
        }
    }
}

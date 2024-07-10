package org.wceng.model;

public enum GptModelType {
    CHAT("POST", "/v1/chat/completions", "application/json"),
    COMPLETION("POST", "/v1/completions", "application/json"),
    IMAGE_GENERATION("POST", "/v1/images/generations", "application/json"),
    EMBEDDING("POST", "/v1/embeddings", "application/json"),
    AUDIO_SPEECH("POST", "/v1/audio/speech", "application/json"),
    AUDIO_TRANSCRIPTION("POST", "/v1/audio/transcriptions", "multipart/form-data"),
    AUDIO_TRANSLATION("POST", "/v1/audio/translations", "multipart/form-data");

    private final String endpoint;
    private final String method;
    private final String contentType;

    GptModelType(String method, String endpoint, String contentType) {
        this.method = method;
        this.endpoint = endpoint;
        this.contentType = contentType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMethod() {
        return method;
    }

    public String getContentType() {
        return contentType;
    }
}

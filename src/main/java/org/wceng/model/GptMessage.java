package org.wceng.model;

public class GptMessage {
    public static String ROLE_SYSTEM = "system";
    public static String ROLE_USER = "user";
    public static String ROLE_ASSISTANT = "assistant";

    private String role;
    private GptImage image;
    private GptAudio audio;
    private String text;

    private GptMessage(Builder builder) {
        this.role = builder.role;
        this.image = builder.image;
        this.audio = builder.audio;
        this.text = builder.text;
    }

    public String getRole() {
        return role;
    }

    public GptAudio getAudio() {
        return audio;
    }

    public GptImage getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public static class Builder {
        private String role;
        private GptImage image;
        private GptAudio audio;
        private String text;

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder image(GptImage image) {
            this.image = image;
            return this;
        }

        public Builder audio(GptAudio audio) {
            this.audio = audio;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public GptMessage build() {
            return new GptMessage(this);
        }

    }

}

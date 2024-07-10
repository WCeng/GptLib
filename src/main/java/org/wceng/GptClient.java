package org.wceng;

import org.wceng.component.GptModelManager;
import org.wceng.component.GptSessionManager;
import org.wceng.launcher.GptLauncherBuilderFactory;

public class GptClient {
    private String baseUrl;
    private String key;

    private final GptModelManager gptModelManager;
    private final GptSessionManager gptSessionManager;
    private final GptLauncherBuilderFactory launcherBuilderFactory;

    private GptClient(GptClientBuilder builder) {
        this.baseUrl = builder.baseUrl;
        this.key = builder.key;

        this.gptModelManager = new GptModelManager();
        this.gptSessionManager = new GptSessionManager(this);
        this.launcherBuilderFactory = new GptLauncherBuilderFactory(this);

        this.getGptSessionManager().createUseSession(builder.sessionName, builder.sessionPrompt);
    }

    public static class GptClientBuilder {
        String baseUrl;
        String key;
        String sessionName;
        String sessionPrompt;

        public GptClientBuilder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public GptClientBuilder setKey(String key) {
            this.key = key;
            return this;
        }

        public GptClientBuilder setSessionName(String sessionName) {
            this.sessionName = sessionName;
            return this;
        }

        public GptClientBuilder setSessionPrompt(String sessionPrompt) {
            this.sessionPrompt = sessionPrompt;
            return this;
        }

        public GptClient build() {
            return new GptClient(this);
        }
    }

    public GptSessionManager getGptSessionManager() {
        return gptSessionManager;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getKey() {
        return key;
    }

    public GptLauncherBuilderFactory getGptLauncherBuilderFactory() {
        return launcherBuilderFactory;
    }
}

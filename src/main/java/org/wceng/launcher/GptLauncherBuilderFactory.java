package org.wceng.launcher;

import org.wceng.GptClient;

public class GptLauncherBuilderFactory {

    private final GptClient gptClient;

    public GptLauncherBuilderFactory(GptClient gptClient) {
        this.gptClient = gptClient;
    }

    public org.wceng.launcher.GptChatLauncher.Builder createGptChatLauncherBuilder() {
        return new org.wceng.launcher.GptChatLauncher.Builder(gptClient);
    }

    public GptImageLauncher.Builder createGptImageLauncherBuilder() {
        return new GptImageLauncher.Builder(gptClient);
    }
}

package org.wceng.model;

import org.wceng.GptClient;
import org.wceng.component.GptChatStack;
import org.wceng.component.GptSessionManager;
import org.wceng.launcher.GptLauncher;

public class GptSession {

    private GptChatStack chatStack;
    private GptSessionManager sessionManager;
    private GptClient gptClient;
    private final String name;
    private final String prompt;
    private GptLauncher gptLauncher;

    public GptSession(GptSessionManager sessionManager, String name, String prompt) {
        this.sessionManager = sessionManager;
        this.name = name;
        this.prompt = prompt;
        this.gptClient = sessionManager.getGptClient();
        this.chatStack = new GptChatStack(this);
    }

    public void bindLauncher(GptLauncher gptLauncher) {
        this.gptLauncher = gptLauncher;
    }

    public GptChatStack getChatStack() {
        return chatStack;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getName() {
        return name;
    }

    public GptSessionManager getSessionManager() {
        return sessionManager;
    }

    public GptClient getGptClient() {
        return gptClient;
    }

}

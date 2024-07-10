package org.wceng.component;

import org.wceng.GptClient;
import org.wceng.launcher.GptLauncher;
import org.wceng.model.GptSession;

import java.util.ArrayList;
import java.util.List;

public class GptSessionManager {
    private GptClient gptClient;
    private GptSession curSession;
    private List<GptSession> sessionList;

    public GptSessionManager(GptClient gptClient) {
        this.gptClient = gptClient;
        this.sessionList = new ArrayList<>();
    }

    public void createUseSession() {
        createUseSession(null, null);
    }

    public void createUseSession(String name, String prompt) {
        GptSession gptSession = new GptSession(this, name, prompt);
        this.curSession = gptSession;
        this.sessionList.add(gptSession);
    }

    public void useSession(GptSession session) {
        if (session != null && sessionList.contains(session)) {
            this.curSession = session;
        }
        throw new RuntimeException("you provided an invalid session");
    }

    public GptSession currentSession() {
        return this.curSession;
    }

    public List<GptSession> sessionList() {
        return this.sessionList;
    }

    public GptClient getGptClient() {
        return gptClient;
    }
}

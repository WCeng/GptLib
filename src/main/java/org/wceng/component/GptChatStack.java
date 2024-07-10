package org.wceng.component;

import org.wceng.model.GptMessage;
import org.wceng.model.GptSession;

import java.util.LinkedList;

public class GptChatStack {

    private final LinkedList<GptMessage> stack;
    private final GptSession session;

    public GptChatStack(GptSession session) {
        this.session = session;
        this.stack = new LinkedList<>();

        String prompt = session.getPrompt();
        if (prompt != null && !prompt.isEmpty()) {
            stack.add(new GptMessage.Builder()
                    .role(GptMessage.ROLE_SYSTEM)
                    .text(prompt)
                    .build()
            );
        }
    }

    public void addMessage(GptMessage message) {
        stack.addLast(message);
    }

    public LinkedList<GptMessage> getStack() {
        return stack;
    }

}

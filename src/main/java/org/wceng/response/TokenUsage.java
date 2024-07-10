package org.wceng.response;

public class TokenUsage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;

    public TokenUsage(int prompt_tokens, int completion_tokens, int total_tokens) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
    }

    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public int getTotal_tokens() {
        return total_tokens;
    }
}

package org.wceng.request;

import org.wceng.model.GptMessage;
import org.wceng.model.GptModel;

import java.util.List;
import java.util.stream.Collectors;

public class GptChatRequestBody extends GptRequest.GptRequestBody {
    private String model;
    private List<GptMessage_Chat> messages;
    private Boolean stream;
    private Float temperature;
    private Integer n;
    private Integer max_tokens;
    private Float frequency_penalty;
    private Integer logit_bias;
    private Boolean logprobs;
    private Integer top_logprobs;
    private Integer presence_penalty;
    private StreamOptions stream_options;

    public GptChatRequestBody(GptModel model, List<GptMessage> messages, Boolean stream, Float temperature, Integer n,
                              Integer max_tokens, Float frequency_penalty, Integer logit_bias, Boolean logprobs,
                              Integer top_logprobs, Integer presence_penalty) {
        this.model = model.getName();
        this.messages = covert(messages);
        this.stream = stream;
        this.temperature = temperature;
        this.n = n;
        this.max_tokens = max_tokens;
        this.frequency_penalty = frequency_penalty;
        this.logit_bias = logit_bias;
        this.logprobs = logprobs;
        this.top_logprobs = top_logprobs;
        this.presence_penalty = presence_penalty;

        if (stream) {
            this.stream_options = new StreamOptions();
        }
    }

    private List<GptMessage_Chat> covert(List<GptMessage> gptMessageList) {
        return gptMessageList.stream().map(message ->
                new GptMessage_Chat(message.getRole(), message.getText())).collect(Collectors.toList());
    }

    public static class StreamOptions {
        private final boolean include_usage = true;
    }

    private static class GptMessage_Chat {
        private final String role;
        private final String content;

        public GptMessage_Chat(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
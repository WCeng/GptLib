package org.wceng.request.body;

import org.wceng.request.GptRequest;

import java.util.List;

public class GptChatCompleteRequestBody extends GptRequest.GptRequestBody {
    private String model;
    private List<ChatMessage> messages;
    private Boolean stream;
    private Float temperature;

    public GptChatCompleteRequestBody(String model, List<ChatMessage> messages, Boolean stream, Float temperature) {
        this.model = model;
        this.messages = messages;
        this.stream = stream;
        this.temperature = temperature;
    }

    public interface ChatMessage {
    }

    public record TextMessage(String role, String content) implements ChatMessage {
    }


    public record VisionMessage(String role, List<ContentBean> content) implements ChatMessage {
        public record ContentBean(String type, String text, ImageUrlBean image_url) {
            public record ImageUrlBean(String url, String detail) {
            }
        }
    }


}
package org.wceng.request;

import org.wceng.model.ChatMessage;
import org.wceng.request.body.GptChatCompleteRequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class GptChatCompleteRequest extends GptRequest<GptChatCompleteRequest.Builder> {

    private GptChatCompleteRequest(Builder builder) {
        super(builder);
    }

    @Override
    public GptRequestBody getRequestBody() {
        List<GptChatCompleteRequestBody.ChatMessage> messages = Arrays.stream(builder.chatMessages).map(
                (Function<ChatMessage, GptChatCompleteRequestBody.ChatMessage>) chatMessage -> {
                    if (chatMessage.images() == null || chatMessage.images().length == 0) {
                        return textMessage(chatMessage);
                    } else {
                        return visionMessage(chatMessage);
                    }
                }).toList();

        return new GptChatCompleteRequestBody(
                builder.model,
                messages,
                builder.stream,
                builder.temperature
        );
    }

    private GptChatCompleteRequestBody.TextMessage textMessage(ChatMessage chatMessage) {
        return new GptChatCompleteRequestBody.TextMessage(
                chatMessage.role(),
                chatMessage.content()
        );
    }

    private GptChatCompleteRequestBody.VisionMessage visionMessage(ChatMessage chatMessage) {
        List<GptChatCompleteRequestBody.VisionMessage.ContentBean> contentBeans = new ArrayList<>();

        contentBeans.add(new GptChatCompleteRequestBody.VisionMessage.ContentBean("text", chatMessage.content(), null));
        contentBeans.addAll(
                Arrays.stream(chatMessage.images()).map(
                        image -> new GptChatCompleteRequestBody.VisionMessage.ContentBean("image_url", null,
                                new GptChatCompleteRequestBody.VisionMessage.ContentBean.ImageUrlBean(image.url(), image.detail()))).toList()
        );

        return new GptChatCompleteRequestBody.VisionMessage(
                chatMessage.role(),
                contentBeans
        );
    }

    public static class Builder extends GptRequestBuilder<Builder> {
        String model;
        Boolean stream;
        Float temperature;
        ChatMessage[] chatMessages;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder stream(Boolean stream) {
            this.stream = stream;
            return this;
        }

        public Builder temperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder messages(ChatMessage... chatMessages) {
            this.chatMessages = chatMessages;
            return this;
        }

        public GptChatCompleteRequest build() {
            return new GptChatCompleteRequest(this);
        }

    }


}

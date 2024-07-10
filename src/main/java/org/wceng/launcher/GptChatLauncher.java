package org.wceng.launcher;


import okhttp3.ResponseBody;
import org.wceng.GptClient;
import org.wceng.model.GptMessage;
import org.wceng.model.GptModel;
import org.wceng.model.GptModelType;
import org.wceng.model.GptSession;
import org.wceng.request.GptChatRequestBody;
import org.wceng.request.GptRequest;
import org.wceng.response.GptChoices;
import org.wceng.response.GptResponse;
import org.wceng.response.StreamHandler;

import java.io.IOException;
import java.util.LinkedList;

public class GptChatLauncher extends org.wceng.launcher.GptLauncher {

    private final Builder builder;

    public GptChatLauncher(Builder builder) {
        super(builder);
        this.builder = builder;
    }

    public static class Builder extends org.wceng.launcher.GptLauncher.Builder<Builder> {

        private GptModel model;
        private Boolean stream = true;
        private Float temperature = null;
        private Integer n = 1;
        private Integer max_tokens = 200;
        private Float frequency_penalty = null;
        private Integer logit_bias = null;
        private Boolean logprobs = null;
        private Integer top_logprobs = null;
        private Integer presence_penalty = null;

        public Builder(GptClient gptClient) {
            super(gptClient);
        }

        /**
         * @param frequency_penalty -2.0 和 2.0 之间的数字。正值会根据新标记在文本中的现有频率对其进行惩罚，从而降低模型逐字重复同一行的可能性。
         */
        public Builder setFrequencyPenalty(float frequency_penalty) {
            this.frequency_penalty = frequency_penalty;
            return this;
        }

        /**
         * @param logit_bias 修改指定标记出现在生成中的可能性。 接受一个 json 对象，该对象将标记（由标记器中的标记 ID 指定）映射到从 -100 到 100 的关联偏差值。从数学上讲，偏差会在采样之前添加到模型生成的 logits 中。确切的效果因模型而异，但 -1 和 1 之间的值应该会减少或增加选择的可能性；像 -100 或 100 这样的值应该导致相关令牌的禁止或独占选择。
         */

        public Builder setLogitBias(int logit_bias) {
            this.logit_bias = logit_bias;
            return this;
        }

        /**
         * @param logprobs 是否返回输出标记的对数概率。如果为 true，则返回message的content中返回的每个输出token的对数概率。
         */
        public Builder setLogprobs(boolean logprobs) {
            this.logprobs = logprobs;
            return this;
        }

        /**
         * @param top_logprobs 0 到 20 之间的整数，指定在每个标记位置返回的最可能标记的数量，每个标记均具有相关的对数概率。如果使用此参数，则logprobs必须设置为true。
         */
        public Builder setTopLogprobs(int top_logprobs) {
            this.top_logprobs = top_logprobs;
            return this;
        }

        /**
         * @param presence_penalty -2.0 和 2.0 之间的数字。正值会根据到目前为止是否出现在文本中来惩罚新标记，从而增加模型谈论新主题的可能性。
         */
        public Builder setPresencePenalty(int presence_penalty) {
            this.presence_penalty = presence_penalty;
            return this;
        }

        /**
         * @param model 要使用的模型的 ID。
         */
        public Builder setModel(GptModel model) {
            this.model = model;
            return this;
        }

        public Builder setStream(boolean stream) {
            this.stream = stream;
            return this;
        }

        public Builder setTemperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setN(int n) {
            this.n = n;
            return this;
        }

        public Builder setMaxTokens(int max_tokens) {
            this.max_tokens = max_tokens;
            return this;
        }

        public GptLauncher build() {
            return new GptChatLauncher(this);
        }
    }


    @Override
    public GptModelType supportedModelType() {
        return GptModelType.CHAT;
    }

    @Override
    public GptSession currentSession() {
        return builder.gptClient.getGptSessionManager().currentSession();
    }

    @Override
    protected GptRequest buildRequest() {
        String url = builder.gptClient.getBaseUrl() + builder.model.getType().getEndpoint();
        String method = builder.model.getType().getMethod();
        String key = builder.gptClient.getKey();
        String contentType = builder.model.getType().getContentType();
        LinkedList<GptMessage> stack = currentSession().getChatStack().getStack();

        GptRequest.GptRequestHeader header = new GptRequest.GptRequestHeader(key, contentType);
        GptChatRequestBody body = new GptChatRequestBody(
                builder.model, stack, builder.stream, builder.temperature, builder.n, builder.max_tokens, builder.frequency_penalty,
                builder.logit_bias, builder.logprobs, builder.top_logprobs, builder.presence_penalty
        );

        return new GptRequest(method, url, header, body);
    }

    @Override
    public GptResponse handleResponse(ResponseBody body) throws IOException {
        StreamHandler streamHandler = null;
        GptChoices gptChoices = null;

        if (builder.stream) {
            streamHandler = new StreamHandler(body.source(), finished -> {
                changeStatue(!finished);
            });
        } else {
            gptChoices = new GptChoices(new GptChoices.Choice(body.string()));
            changeStatue(false);
        }

        return new GptResponse.GptResponseBuilder()
                .stream(builder.stream)
                .gptChoices(gptChoices)
                .streamHandler(streamHandler)
                .data(null)
                .usage(null)
                .build();
    }

}

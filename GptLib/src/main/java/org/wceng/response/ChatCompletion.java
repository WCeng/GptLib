package org.wceng.response;

import java.util.List;

public class ChatCompletion extends GptResponse{

    /**
     * id : chatcmpl-123
     * object : chat.completion
     * created : 1677652288
     * model : gpt-3.5-turbo-0125
     * system_fingerprint : fp_44709d6fcb
     * choices : [{"index":0,"message":{"role":"assistant","content":"\n\nHello there, how may I assist you today?"},"logprobs":null,"finish_reason":"stop"}]
     * usage : {"prompt_tokens":9,"completion_tokens":12,"total_tokens":21}
     */

    private String id;
    private String object;
    private int created;
    private String model;
    private String system_fingerprint;
    private UsageBean usage;
    private List<ChoicesBean> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

    public void setSystem_fingerprint(String system_fingerprint) {
        this.system_fingerprint = system_fingerprint;
    }

    public UsageBean getUsage() {
        return usage;
    }

    public void setUsage(UsageBean usage) {
        this.usage = usage;
    }

    public List<ChoicesBean> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoicesBean> choices) {
        this.choices = choices;
    }

    public static class UsageBean {
        /**
         * prompt_tokens : 9
         * completion_tokens : 12
         * total_tokens : 21
         */

        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public void setPrompt_tokens(int prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public int getCompletion_tokens() {
            return completion_tokens;
        }

        public void setCompletion_tokens(int completion_tokens) {
            this.completion_tokens = completion_tokens;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }

        public void setTotal_tokens(int total_tokens) {
            this.total_tokens = total_tokens;
        }
    }

    public static class ChoicesBean {
        /**
         * index : 0
         * message : {"role":"assistant","content":"\n\nHello there, how may I assist you today?"}
         * logprobs : null
         * finish_reason : stop
         */

        private int index;
        private MessageBean message;
        private Object logprobs;
        private String finish_reason;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public MessageBean getMessage() {
            return message;
        }

        public void setMessage(MessageBean message) {
            this.message = message;
        }

        public Object getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(Object logprobs) {
            this.logprobs = logprobs;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }

        public static class MessageBean {
            /**
             * role : assistant
             * content :

             Hello there, how may I assist you today?
             */

            private String role;
            private String content;

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}

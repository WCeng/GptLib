package org.wceng.response;

import java.util.List;

public class Embedding {


    /**
     * object : list
     * data : [{"object":"embedding","embedding":[0.0023064255,-0.009327292,".... (1536 floats total for ada-002)         -0.0028842222"],"index":0}]
     * model : text-embedding-ada-002
     * usage : {"prompt_tokens":8,"total_tokens":8}
     */

    private String object;
    private String model;
    private UsageBean usage;
    private List<DataBean> data;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UsageBean getUsage() {
        return usage;
    }

    public void setUsage(UsageBean usage) {
        this.usage = usage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class UsageBean {
        /**
         * prompt_tokens : 8
         * total_tokens : 8
         */

        private int prompt_tokens;
        private int total_tokens;

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public void setPrompt_tokens(int prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }

        public void setTotal_tokens(int total_tokens) {
            this.total_tokens = total_tokens;
        }
    }

    public static class DataBean {
        /**
         * object : embedding
         * embedding : [0.0023064255,-0.009327292,".... (1536 floats total for ada-002)         -0.0028842222"]
         * index : 0
         */

        private String object;
        private int index;
        private List<Double> embedding;

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<Double> getEmbedding() {
            return embedding;
        }

        public void setEmbedding(List<Double> embedding) {
            this.embedding = embedding;
        }
    }
}

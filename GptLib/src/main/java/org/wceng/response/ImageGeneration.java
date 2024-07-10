package org.wceng.response;

import java.util.List;

public class ImageGeneration extends GptResponse{
    /**
     * created : 1589478378
     * data : [{"url":"https://..."},{"url":"https://..."}]
     */

    private int created;
    private List<DataBean> data;

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : https://...
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

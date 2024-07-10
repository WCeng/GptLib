package org.wceng.request;

import org.wceng.util.GsonUtil;

public class GptRequest {

    private final String method;
    private final String url;
    private final GptRequestHeader requestHeader;
    private final GptRequestBody requestBody;

    public static class GptRequestHeader {
        private final String authorization;
        private final String contentType;

        public GptRequestHeader(String authorization, String contentType) {
            this.authorization = authorization;
            this.contentType = contentType;
        }
        public String getAuthorization() {
            return authorization;
        }

        public String getContentType() {
            return contentType;
        }
    }

    public static class GptRequestBody {

        @Override
        public String toString() {
            return GsonUtil.toJson(this);
        }
    }


    public GptRequest(String method, String url, GptRequestHeader header, GptRequestBody body) {
        this.method = method;
        this.url = url;
        this.requestHeader = header;
        this.requestBody = body;
    }

    public GptRequest(String method, String url, GptRequestHeader header) {
        this(method, url, header, null);
    }


    public String getMethod() {
        return method;
    }

    public GptRequestBody getRequestBody() {
        return requestBody;
    }

    public String getUrl() {
        return url;
    }

    public GptRequestHeader getRequestHeader() {
        return requestHeader;
    }
}

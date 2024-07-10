package org.wceng.request;

import org.wceng.util.GsonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class GptRequest<T extends GptRequest.GptRequestBuilder<T>> {

    final T builder;

    public GptRequest(T t) {
        builder = t;
    }

    public record GptRequestHeader(String authorization, String contentType) {
    }

    public static class GptRequestBody {
        @Override
        public String toString() {
            return GsonUtil.toJson(this);
        }
    }

    public static class GptFormRequestBody extends GptRequestBody {
        public List<File> files = new ArrayList<>();
        public Map<String, String> map = new LinkedHashMap<>();
    }


    public String getMethod() {
        return builder.method;
    }

    public abstract GptRequestBody getRequestBody();

    public String getUrl() {
        return builder.url;
    }

    public GptRequestHeader getRequestHeader() {
        return new GptRequestHeader(builder.authorization, builder.contentType);
    }


    public static class GptRequestBuilder<T> {
        protected String method;
        protected String url;
        protected String authorization;
        protected String contentType;

        @SuppressWarnings("unchecked")
        public T method(String method) {
            this.method = method;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T url(String url) {
            this.url = url;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T authorization(String authorization) {
            this.authorization = authorization;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T contentType(String contentType) {
            this.contentType = contentType;
            return (T) this;
        }
    }

}

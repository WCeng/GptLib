package org.wceng.component;

import okhttp3.*;
import org.wceng.request.GptRequest;
import org.wceng.response.converter.GptResponseConverter;
import org.wceng.response.converter.GptResponseConverterImpl;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class GptLauncher {

    private final OkHttpClient httpClient;

    private GptLauncher(Builder builder) {
        httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(builder.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(builder.readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    public static class Builder {
        long connectTimeout = 60 * 1000;
        long readTimeout = 60 * 1000;

        public Builder setConnectTimeout(long timeout) {
            this.connectTimeout = timeout;
            return this;
        }

        public Builder setReadTimeout(long timeout) {
            this.readTimeout = timeout;
            return this;
        }

        public GptLauncher build() {
            return new GptLauncher(this);
        }
    }

    private RequestBody buildHttpRequestBody(GptRequest request) {
        if (request.getRequestHeader().contentType().equals("application/json")) {
            MediaType mediaType = MediaType.parse(request.getRequestHeader().contentType());
            String body = request.getRequestBody().toString();
            System.out.println("requestBody: " + body);
            return RequestBody.create(body, mediaType);
        } else if (request.getRequestHeader().contentType().equals("multipart/form-data")) {
            if (request.getRequestBody() instanceof GptRequest.GptFormRequestBody requestBody) {
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);

                for (File file : requestBody.files) {
                    builder.addFormDataPart("file", file.getName(),
                            RequestBody.create(file, MediaType.parse(request.getRequestHeader().contentType())));
                }
                requestBody.map.forEach((key, value) -> {
                    if (value != null) {
                        builder.addFormDataPart(key, value);
                    }
                });
                return builder.build();
            }
            throw new RuntimeException("error in provided request body");
        } else {
            throw new RuntimeException("unsupported request type");
        }
    }

    public GptResponseConverter execute(GptRequest gptRequest) throws Exception {
        RequestBody httpRequestBody = buildHttpRequestBody(gptRequest);

        Request httpRequest = new Request.Builder()
                .url(gptRequest.getUrl())
                .method(gptRequest.getMethod(), httpRequestBody)
                .addHeader("Authorization", "Bearer " + gptRequest.getRequestHeader().authorization())
                .addHeader("Content-Type", gptRequest.getRequestHeader().contentType())
                .build();

        return new GptResponseConverterImpl(httpClient.newCall(httpRequest).execute());
    }

}

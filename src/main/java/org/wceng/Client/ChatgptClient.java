package org.wceng.Client;

import okhttp3.*;
import org.wceng.util.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class ChatgptClient {

    public static String API_KEY_FREE = "sk-cdD2Ob65SNNX3IocJK62t62CUvjlfL3rOBDUbj2FxzJyyoDJ";
    public static String API_KEY_VIP = "sk-luQ653aLv9Su9O0tzOtcOsM3He1RlEpRzOxusIO46NU3pPmL";

    private static final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build();

    public static void main(String[] args) {
        try {
//            modelListTest();
//            chatTest();
//            text_davinci_003();
//            image();
//            text_embedding_ada_002();
//            tts_1();
//            audio_transcriptions();
            audio_translations();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void modelListTest() throws Exception {

        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/models")
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void chatTest() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": \"You are a helpful assistant.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"做网站的步骤\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"assistant\",\n" +
                "      \"content\": \"不知道\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"你的上一个回答是什么!\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"stream\":\"false\"\n" +
                "}");
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/chat/completions")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
//        SSEUtil.handleSSEStream(response);
//        System.out.println(response.body().string());
    }

    private static void text_davinci_003() throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"model\": \"text-davinci-003\",\n  \"prompt\": \"Say this is a test\",\n  \"max_tokens\": 7,\n  \"temperature\": 0,\n  \"top_p\": 1,\n  \"n\": 1,\n  \"stream\": false,\n  \"logprobs\": null,\n  \"stop\": \"\\n\"\n}");
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/completions")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void image() throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"prompt\": \"A colorful sunset over the mountains\",\n  \"n\": 1,\n  \"model\": \"dall-e-3\",\n  \"size\": \"1024x1024\"\n}");
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/images/generations")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void text_embedding_ada_002() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"model\": \"text-embedding-ada-002\",\n  \"input\": \"The food was delicious and the waiter...\"\n}");
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/embeddings")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void tts_1() throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"model\": \"tts-1\",\n    \"input\": \"The quick brown fox jumped over the lazy dog.\",\n    \"voice\": \"nova\"\n}");
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/audio/speech")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = response.body().();
//        System.out.println(response.body().string());


        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\王程程\\Desktop\\test\\audio.mp3");
        fileOutputStream.write(inputStream.readAllBytes());

        inputStream.close();
        fileOutputStream.close();
    }

    private static void audio_transcriptions() throws Exception {
        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "audio.mp3",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("C:\\Users\\王程程\\Desktop\\test\\audio.mp3")))
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("prompt", "eiusmod nulla")
                .addFormDataPart("response_format", "json")
                .addFormDataPart("temperature", "0")
                .addFormDataPart("language", "")
                .build();

        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/audio/transcriptions")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void audio_translations() throws Exception {
        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", "audio.mp3",
                        RequestBody.create(
                                IOUtil.fileToByteArray(new File("C:\\Users\\王程程\\Desktop\\test\\audio.mp3")),
                                MediaType.parse("application/octet-stream")))
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("prompt", "")
                .addFormDataPart("response_format", "json")
                .addFormDataPart("temperature", "0")
                .build();
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.tech/v1/audio/translations")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_KEY_VIP)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}

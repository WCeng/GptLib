package org.wceng.response.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Response;
import org.wceng.response.*;
import org.wceng.util.GsonUtil;

public class GptResponseConverterImpl implements GptResponseConverter {

    private final Response response;

    public GptResponseConverterImpl(Response response) {
        this.response = response;
    }

    @Override
    public ChatCompletion toChatCompletion() throws Exception {
        String string = response.body().string();
        return GsonUtil.fromJson(string, ChatCompletion.class);
    }

    @Override
    public ImageGeneration toImageGeneration() throws Exception {
        return GsonUtil.fromJson(response.body().string(), ImageGeneration.class);
    }

    @Override
    public AudioSpeed toAudioSpeed() throws Exception {
        AudioSpeed audioSpeed = new AudioSpeed();
        audioSpeed.setData(response.body().byteStream().readAllBytes());
        return audioSpeed;
    }

    @Override
    public AudioTranscription toAudioTranscription() throws Exception {
        return GsonUtil.fromJson(response.body().string(), AudioTranscription.class);
    }

    @Override
    public AudioTranslation toAudioTranslation() throws Exception {
        return GsonUtil.fromJson(response.body().string(), AudioTranslation.class);
    }

    @Override
    public Embedding toEmbedding() throws Exception {
        return GsonUtil.fromJson(response.body().string(), Embedding.class);
    }
}

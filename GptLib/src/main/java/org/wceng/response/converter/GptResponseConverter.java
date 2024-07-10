package org.wceng.response.converter;

import okhttp3.Response;
import org.wceng.response.*;

public interface GptResponseConverter {

    ChatCompletion toChatCompletion() throws Exception;

    ImageGeneration toImageGeneration() throws Exception;

    AudioSpeed toAudioSpeed() throws Exception;

    AudioTranscription toAudioTranscription() throws Exception;

    AudioTranslation toAudioTranslation() throws Exception;

    Embedding toEmbedding() throws Exception;

}

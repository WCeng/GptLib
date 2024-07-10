package org.wceng.model;

import java.util.HashMap;
import java.util.Map;

public enum GptModel {

    TEXT_EMBEDDING_ADA_002("text-embedding-ada-002", org.wceng.model.GptModelType.EMBEDDING),
    TEXT_EMBEDDING_3_SMALL("text-embedding-3-small", org.wceng.model.GptModelType.EMBEDDING),
    TEXT_EMBEDDING_3_LARGE("text-embedding-3-large", GptModelType.EMBEDDING),
    GPT_3_5_TURBO_CA("gpt-3.5-turbo-ca", GptModelType.CHAT),
    GPT_4O_CA("gpt-4o-ca", GptModelType.CHAT),
    GPT_4_TURBO_CA("gpt-4-turbo-ca", GptModelType.CHAT),
    GPT_4_TURBO_PREVIEW_CA("gpt-4-turbo-preview-ca", GptModelType.CHAT),
    GPT_4_CA("gpt-4-ca", GptModelType.CHAT),
    CLAUDE_3_5_SONNET_20240620("claude-3-5-sonnet-20240620", GptModelType.CHAT),
    GPT_3_5_TURBO("gpt-3.5-turbo", GptModelType.CHAT),
    GPT_3_5_TURBO_0125("gpt-3.5-turbo-0125", GptModelType.CHAT),
    GPT_3_5_TURBO_1106("gpt-3.5-turbo-1106", GptModelType.CHAT),
    GPT_3_5_TURBO_0613("gpt-3.5-turbo-0613", GptModelType.CHAT),
    GPT_3_5_TURBO_0301("gpt-3.5-turbo-0301", GptModelType.CHAT),
    GPT_3_5_TURBO_INSTRUCT("gpt-3.5-turbo-instruct", GptModelType.CHAT),
    GPT_3_5_TURBO_INSTRUCT_0914("gpt-3.5-turbo-instruct-0914", GptModelType.CHAT),
    GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k", GptModelType.CHAT),
    GPT_3_5_TURBO_16K_0613("gpt-3.5-turbo-16k-0613", GptModelType.CHAT),
    GPT_4O("gpt-4o", GptModelType.CHAT),
    GPT_4O_2024_05_13("gpt-4o-2024-05-13", GptModelType.CHAT),
    GPT_4_TURBO("gpt-4-turbo", GptModelType.CHAT),
    GPT_4_TURBO_2024_04_09("gpt-4-turbo-2024-04-09", GptModelType.CHAT),
    GPT_4_TURBO_PREVIEW("gpt-4-turbo-preview", GptModelType.CHAT),
    GPT_4_1106_PREVIEW("gpt-4-1106-preview", GptModelType.CHAT),
    GPT_4_0125_PREVIEW("gpt-4-0125-preview", GptModelType.CHAT),
    GPT_4_VISION_PREVIEW("gpt-4-vision-preview", GptModelType.CHAT),
    GPT_4_1106_VISION_PREVIEW("gpt-4-1106-vision-preview", GptModelType.CHAT),
    GPT_4("gpt-4", GptModelType.CHAT),
    GPT_4_0613("gpt-4-0613", GptModelType.CHAT),
    WHISPER_1("whisper-1", GptModelType.AUDIO_TRANSCRIPTION),
    TTS_1("tts-1", GptModelType.AUDIO_SPEECH),
    TTS_1_1106("tts-1-1106", GptModelType.AUDIO_SPEECH),
    TTS_1_HD("tts-1-hd", GptModelType.AUDIO_SPEECH),
    TTS_1_HD_1106("tts-1-hd-1106", GptModelType.AUDIO_SPEECH),
    DALL_E_2("dall-e-2", GptModelType.IMAGE_GENERATION),
    DALL_E_3("dall-e-3", GptModelType.IMAGE_GENERATION);

    private static final Map<String, GptModel> BY_NAME = new HashMap<>();

    static {
        for (GptModel model : values()) {
            BY_NAME.put(model.getName(), model);
        }
    }

    private final String name;
    private final GptModelType type;

    GptModel(String name, GptModelType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public GptModelType getType() {
        return type;
    }

    public static GptModel fromName(String name) {
        return BY_NAME.get(name);
    }
}



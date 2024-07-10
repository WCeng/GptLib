package org.wceng;

import org.wceng.component.GptLauncher;
import org.wceng.model.ChatMessage;
import org.wceng.request.*;
import org.wceng.response.*;
import org.wceng.util.ImageUtil;

import java.io.File;

public class Main {

    static String baseUrl = "https://api.chatanywhere.tech";
    static String key = "sk-luQ653aLv9Su9O0tzOtcOsM3He1RlEpRzOxusIO46NU3pPmL";

    public static void main(String[] args) {
        GptLauncher launcher = new GptLauncher.Builder()
                .setReadTimeout(2 * 1000 * 60).build();

        try {
//            chat_complete(launcher);
//            image_generation(launcher);
//            audio_speed(launcher);
//            audio_transcription(launcher);
//            audio_translation(launcher);
            chat_with_vision(launcher);
//            embedding(launcher);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void embedding(GptLauncher launcher) throws Exception {
        GptRequest<GptEmbeddingRequest.Builder> request = new GptEmbeddingRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/embeddings")
                .contentType("application/json")
                .method("POST")
                .model("text-embedding-ada-002")
                .input("The food was delicious and the waiter...")
                .build();

        Embedding embedding = launcher.execute(request).toEmbedding();
        System.out.println(embedding.getData().get(0).getEmbedding());

    }

    private static void chat_with_vision(GptLauncher launcher) throws Exception {
        ChatMessage.Image image1 = new ChatMessage.Image(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Gfp-wisconsin-madison-the-nature-boardwalk.jpg/2560px-Gfp-wisconsin-madison-the-nature-boardwalk.jpg",
                "low"
        );

        ChatMessage.Image image2 = new ChatMessage.Image(
                "data:image/jpeg;base64," + ImageUtil.encodeImageToBase64("C:\\Users\\王程程\\Desktop\\0a49073fadbf265771dfc6a26d87c3b.jpg"),
                "low"
        );

        GptChatCompleteRequest request = new GptChatCompleteRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/chat/completions")
                .contentType("application/json")
                .method("POST")
                .model("gpt-4o-ca")
                .temperature(0.5f)
                .stream(false)
                .messages(
//                        new ChatMessage("system", "分别分析图片构图（不用描述图片内容）"),
//                        new ChatMessage("user", "明白我的意思吗， 如果明白， 请回答你明白的内容， 反之， 回答不知道")
                        new ChatMessage("user", "这只我的图片", image2),
                        new ChatMessage("user", "你觉得这本书怎么样？")
                )
                .build();


        ChatCompletion chatCompletion = launcher.execute(request).toChatCompletion();
        String content = chatCompletion.getChoices().get(0).getMessage().getContent();
        System.out.println(content);
    }


    private static void audio_translation(GptLauncher launcher) throws Exception {
        GptRequest<GptAudioTranslationRequest.Builder> request = new GptAudioTranslationRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/audio/translations")
                .contentType("multipart/form-data")
                .method("POST")
                .file(new File("C:\\Users\\王程程\\Desktop\\test\\audio.mp3"))
                .temperature(0.5f)
                .model("whisper-1")
                .prompt("speed slowly")
                .build();

        AudioTranslation audioTranslation = launcher.execute(request).toAudioTranslation();
        String text = audioTranslation.getText();
        System.out.println(text);
    }

    private static void audio_transcription(GptLauncher launcher) throws Exception {
        GptAudioTranscriptionRequest request = new GptAudioTranscriptionRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/audio/transcriptions")
                .contentType("multipart/form-data")
                .method("POST")
                .file(new File("C:\\Users\\王程程\\Desktop\\test\\audio.mp3"))
                .temperature(0.5f)
                .model("whisper-1")
                .build();

        AudioTranscription audioTranscription = launcher.execute(request).toAudioTranscription();
        String text = audioTranscription.getText();
        System.out.println(text);
    }

    private static void audio_speed(GptLauncher launcher) throws Exception {
        GptAudioSpeechRequest request = new GptAudioSpeechRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/audio/speech")
                .contentType("application/json")
                .method("POST")
                .input("文本转换为浮点数集合的过程通常被称为“文本向量化”或“嵌入”。这个过程将文本数据转换为计算机可以处理的数值格式。在自然语言处理（NLP）和机器学习领域，文本向量化是一个关键步骤，因为大多数机器学习算法和模型只能处理数值数据。\n" +
                        "\n" +
                        "以下是文本向量化的一些常见方法：\n" +
                        "\n" +
                        "1. **词袋模型（Bag of Words, BoW）**：将文本表示为词的频率向量。\n" +
                        "2. **TF-IDF（Term Frequency-Inverse Document Frequency）**：在词袋模型的基础上，考虑词在文档集中的重要性。\n" +
                        "3. **词嵌入（Word Embeddings）**：如 Word2Vec、GloVe 以及 FastText，它们将词表示为高维向量，这些向量捕捉了词的语义关系。\n" +
                        "4. **上下文嵌入（Contextual Embeddings）**：如 BERT、GPT 等，它们不仅考虑单个词，还考虑词在上下文中的位置和意义。\n")
                .model("tts-1")
                .voice("fable")
                .responseFormat("mp3")
                .speed(1f)
                .build();
        AudioSpeed audioSpeed = launcher.execute(request).toAudioSpeed();
        audioSpeed.toFile(new File("C:\\Users\\王程程\\Desktop\\test\\audio.mp3"));
    }

    //
    private static void image_generation(GptLauncher launcher) throws Exception {
        GptImageGenerationRequest request = new GptImageGenerationRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/images/generations")
                .contentType("application/json")
                .method("POST")
                .model("dall-e-3")
                .n(1)
                .prompt("A cute baby sea otter")
                .size("1024x1024")
                .build();
        ImageGeneration imageGeneration = launcher.execute(request).toImageGeneration();
        System.out.println(imageGeneration.getData().get(0).getUrl());
    }

    private static void chat_complete(GptLauncher launcher) throws Exception {
        GptChatCompleteRequest request = new GptChatCompleteRequest.Builder()
                .authorization(key)
                .url(baseUrl + "/v1/chat/completions")
                .contentType("application/json")
                .method("POST")
                .model("gpt-4o-ca")
                .stream(false)
                .messages(
//                        new ChatMessage(
//                                "system",
//                                "你需要根据新华字典来解释汉字(可能是多音字)，需要给出汉字的拼音,解释（如果有多个解释标上序号）， 如果是多音字，需要回答每个拼音对应的字的解释。例如：行"
//                        ),
//                        new ChatMessage("user", "槛"),
//                        new ChatMessage("assistant", "不知道"),
                        new ChatMessage("user", "把文本转换成向量的浮点数集合， 这些浮点数有什么用")
                )
                .temperature(0.7f)
                .build();

        ChatCompletion chatCompletion = launcher.execute(request).toChatCompletion();
        String content = chatCompletion.getChoices().get(0).getMessage().getContent();
        System.out.println(content);
    }
}
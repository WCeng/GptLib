package org.wceng.Client;

import org.wceng.GptClient;
import org.wceng.launcher.GptImageLauncher;
import org.wceng.launcher.GptLauncher;
import org.wceng.launcher.GptResponseCallback;
import org.wceng.model.GptMessage;
import org.wceng.model.GptModel;
import org.wceng.response.GptResponse;
import org.wceng.response.StreamListener;

public class TestClient {

    static String baseUrl = "https://api.chatanywhere.tech";
    static String key = "sk-luQ653aLv9Su9O0tzOtcOsM3He1RlEpRzOxusIO46NU3pPmL";

    public static void main(String[] args) {
        GptClient gptClient = new GptClient.GptClientBuilder()
                .setKey(key)
                .setBaseUrl(baseUrl)
                .setSessionName("充当翻译")
                .setSessionPrompt("你需要把英文翻译中文，只需要回答翻译后的结果，例如：apple")
                .build();

        GptLauncher gptLauncher = gptClient.getGptLauncherBuilderFactory().createGptChatLauncherBuilder()
                .setMaxTokens(200)
                .setModel(GptModel.GPT_3_5_TURBO_CA)
                .setN(1)
                .setStream(true)
                .setTemperature(0.7f)
                .setConnectTimeout(60 * 1000)
                .setReadTimeout(60 * 1000)
                .setResponseCallback(new GptResponseCallback() {
                    @Override
                    public void onReceived(GptResponse response) {
                        if (response.isStream()) {
                            response.getStreamHandler().read(new MyStreamListener());
                        } else {
                            if (!response.getGptChoices().isEmpty()) {
                                String text = response.getGptChoices().get(0).getText();
                                System.out.println(text);
                            }
                        }
                    }

                    @Override
                    public void onError(Exception error) {
                        System.out.println(error.getMessage());
                    }

                    @Override
                    public void onStatue(boolean active) {
                        System.out.println("statue: " + active);
                    }
                })
                .build();

        GptMessage message = new GptMessage.Builder()
                .role(GptMessage.ROLE_USER)
                .text("this is a test message")
                .build();

        gptLauncher.sendMessage(message);


        GptImageLauncher imageLauncher = gptClient.getGptLauncherBuilderFactory().createGptImageLauncherBuilder()
                .setN(1)
                .setPrompt("A colorful sunset over the mountains")
                .setSize("1024x1024")
                .setModel(GptModel.DALL_E_2)
                .build();
        imageLauncher.sendMessage(new GptMessage.Builder()
                .text("A colorful sunset over the mountains")
                .build());

    }


    public static class MyStreamListener implements StreamListener {
        @Override
        public void onReadUtf8Line(String line) {
            System.out.println(line);
            System.out.println("----------------------------------");
        }

        @Override
        public void onReadBytes(byte[] bytes) {

        }

        @Override
        public void onReadError(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

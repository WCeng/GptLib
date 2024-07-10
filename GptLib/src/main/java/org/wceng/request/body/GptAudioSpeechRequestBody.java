package org.wceng.request.body;

import okhttp3.RequestBody;
import org.wceng.request.GptRequest;

public class GptAudioSpeechRequestBody extends GptRequest.GptRequestBody {


    /**
     * model : tts-1
     * input : The quick brown fox jumped over the lazy dog.
     * voice : alloy
     */

    private String model;
    private String input;
    private String voice;
    private String response_format;
    private Float speed;

    public GptAudioSpeechRequestBody(String model, String input, String voice, String response_format, Float speed) {
        this.model = model;
        this.input = input;
        this.voice = voice;
        this.response_format = response_format;
        this.speed = speed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}

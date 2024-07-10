package org.wceng.request.body;

import org.wceng.request.GptRequest;

public class GptImageGenerationRequestBody extends GptRequest.GptRequestBody {


    /**
     * model : dall-e-3
     * prompt : A cute baby sea otter
     * n : 1
     * size : 1024x1024
     */

    private String model;
    private String prompt;
    private int n;
    private String size;

    public GptImageGenerationRequestBody(String model, String prompt, int n, String size) {
        this.model = model;
        this.prompt = prompt;
        this.n = n;
        this.size = size;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

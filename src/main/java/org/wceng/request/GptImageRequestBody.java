package org.wceng.request;

import org.wceng.model.GptModel;

public class GptImageRequestBody extends GptRequest.GptRequestBody {
    private String prompt;
    private int n;
    private String model;
    private String size;

    public GptImageRequestBody(String prompt, int n, String model, String size) {
        this.prompt = prompt;
        this.n = n;
        this.model = model;
        this.size = size;
    }

    public String getPrompt() {
        return prompt;
    }

    public int getN() {
        return n;
    }

    public String getModel() {
        return model;
    }

    public String getSize() {
        return size;
    }
}

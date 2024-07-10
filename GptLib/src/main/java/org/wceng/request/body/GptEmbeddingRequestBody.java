package org.wceng.request.body;

import org.wceng.request.GptRequest;

public class GptEmbeddingRequestBody extends GptRequest.GptRequestBody {


    /**
     * model : text-embedding-ada-002
     * input : The food was delicious and the waiter...
     */

    private String model;
    private String input;

    public GptEmbeddingRequestBody(String model, String input) {
        this.model = model;
        this.input = input;
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
}

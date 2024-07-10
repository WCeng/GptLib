package org.wceng.component;

import org.wceng.model.GptModel;

public class GptModelManager {

    private GptModel usingModel;

    public GptModel using() {
        return usingModel;
    }

    public void use(GptModel model) {
        usingModel = model;
    }

}

package org.wceng.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GptChoices {

    private final List<Choice> choices;

    public GptChoices(Choice... choices) {
        if (choices == null || choices.length == 0) {
            this.choices = new ArrayList<Choice>();
        } else {
            this.choices = Arrays.stream(choices).toList();
        }
    }

    public boolean isEmpty() {
        return choices.isEmpty();
    }

    public Choice get(int index) {
        return choices.get(index);
    }

    public static class Choice {
        String text;

        public Choice(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}

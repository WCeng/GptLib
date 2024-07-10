package org.wceng.launcher;

import org.wceng.response.GptResponse;

public interface GptResponseCallback {
    void onReceived(GptResponse response);

    void onError(Exception error);

    void onStatue(boolean active);
}

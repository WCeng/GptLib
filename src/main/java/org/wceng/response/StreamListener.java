package org.wceng.response;

public interface StreamListener {

    void onReadUtf8Line(String line);

    void onReadBytes(byte[] bytes);

    void onReadError(Exception e);
}

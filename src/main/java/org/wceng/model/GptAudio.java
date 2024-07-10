package org.wceng.model;

public class GptAudio {
    private String name;
    private byte[] data;
    private String format;

    public GptAudio(String name, byte[] data, String format) {
        this.name = name;
        this.data = data;
        this.format = format;
    }

    public GptAudio() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

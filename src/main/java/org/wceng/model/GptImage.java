package org.wceng.model;

public class GptImage {
    private String name;
    private String url;
    private byte[] data;
    private String format;

    public GptImage() {
    }

    public GptImage(String name, String url, byte[] data, String format) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
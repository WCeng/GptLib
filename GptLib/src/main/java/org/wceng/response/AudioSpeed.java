package org.wceng.response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioSpeed extends GptResponse {
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void toFile(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            fos.flush();
        }
    }
}

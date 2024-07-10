package org.wceng.response;

import okio.BufferedSource;

public class StreamHandler {


    private final BufferedSource source;
    private final ReadStatue readStatue;

   public interface ReadStatue{
        void onFinished(boolean finished);
    }


    public StreamHandler(BufferedSource source, ReadStatue statue) {
        this.source = source;
        this.readStatue = statue;
    }

    public void read(StreamListener streamListener) {
        try {
            while (!source.exhausted()) {
                String line = source.readUtf8Line();
                streamListener.onReadUtf8Line(line);
//                if (line != null && !line.isEmpty()) {
//                    if (line.startsWith("data: ")) {
//                        // 处理事件数据行
//                        String eventData = line.substring("data: ".length());
//                        System.out.println(eventData);
//                    } else if (line.startsWith(":")) {
//                        // 忽略注释行
//                        continue;
//                    } else {
//                        // 处理其他类型的行，根据需要进行处理
//                        System.out.println("Unhandled line: " + line);
//                    }
//                }
            }
            readStatue.onFinished(true);
        } catch (Exception e) {
            streamListener.onReadError(e);
        }
    }


}

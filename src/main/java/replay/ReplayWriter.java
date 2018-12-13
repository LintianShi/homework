package replay;

import java.io.FileWriter;

public class ReplayWriter {
    private static FileWriter out;
    public ReplayWriter() {
        ;
    }
    public void setOut(FileWriter out) {
        ReplayWriter.out = out;
    }
    public void write(String data) throws Exception {
        out.write(data);
    }
    public void close() throws Exception {
        out.close();
    }
}

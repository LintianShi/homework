package replay;

import java.io.FileWriter;

public class ReplayWriter {
    private FileWriter out;
    public ReplayWriter(FileWriter out) {
        this.out = out;
    }
    public void write(String data) throws Exception {
        out.write(data);
    }
    public void close() throws Exception {
        out.close();
    }
}

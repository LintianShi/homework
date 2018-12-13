package creature;
import java.io.*;
import javafx.scene.image.*;
public class Demon extends Creature {
    public Demon(String name, String imgPath) {
        super(name, imgPath, true);
    }
    public Demon (String name) {
        super(name, "image/Demon1.png", true);
    }
    public Demon () {
        super("小喽喽", "image/Demon1.png", true);
    }
}

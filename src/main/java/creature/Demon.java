package creature;
import java.io.*;
import javafx.scene.image.*;
public class Demon extends Creature {
    public Demon() {
        name = "小喽喽";
        alive = true;
        evil = true;
        coordinateX = -1;
        coordinateY = -1;
        imgPath = "image/Demon1.png";
        //image = new Image("image/loulou.jpg");
    }
    public Demon (String name) {
        this.name = name;
        alive = true;
        evil = true;
        coordinateX = -1;
        coordinateY = -1;
        imgPath = "image/Demon1.png";
        //image = new Image("image/loulou.jpg");
    }
}

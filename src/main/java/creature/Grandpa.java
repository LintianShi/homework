package creature;

import javafx.scene.image.*;
import java.io.File;
import java.io.IOException;

public class Grandpa extends Creature {
    public Grandpa() {
        name = "爷爷";
        alive = true;
        evil = false;
        coordinateX = -1;
        coordinateY = -1;
        imgPath = "image/Yeye.png";
        //image = new Image("image/grandpa.jpg");
    }
}

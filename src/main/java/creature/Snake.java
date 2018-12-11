package creature;

import javafx.scene.image.*;
import java.io.File;
import java.io.IOException;

public class Snake extends Demon {
    public Snake() {
        name = "蛇精";
        alive = true;
        evil = true;
        coordinateX = -1;
        coordinateY = -1;
        imgPath = "image/Snake.png";
        //image = new Image("image/snake.jpg");
    }
}

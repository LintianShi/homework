package gui;

import space.Coordinate;

import java.util.concurrent.TimeUnit;

public class GUIRefresher implements Runnable {
    private GameController controller;
    private boolean working;
    public GUIRefresher(GameController controller) {
        this.controller = controller;
        working = true;
    }
    public void run() {
        while (working) {
            synchronized (Coordinate.class) {
                controller.display();
            }
            //System.out.println(1);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void close() {
        working = false;
    }
}

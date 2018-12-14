package gui;

import creature.Creature;
import creature.HuluWa;
import javafx.application.Platform;
import replay.ReplayWriter;
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
            System.out.println("working");
            if (controller.getBattle().huluBrothers.isAllDead()) {
                //controller.setLabel("HuluWa Win!");
                for (Creature c : controller.getBattle().monsters.member) {
                    c.close();
                }
                controller.getBattle().monsters.observer.close();
                try {
                    new ReplayWriter().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                controller.display();
                controller.setFighting(true);
                Platform.runLater(new Runnable() {
                    public void run() {
                        controller.setLabel("Demons Win!");
                    }
                });
                break;
            }
            if (controller.getBattle().monsters.isAllDead()) {
                //controller.setLabel("Demons Win!");
                for (HuluWa h : controller.getBattle().huluBrothers.member) {
                    h.close();
                }
                controller.getBattle().monsters.observer.close();
                try {
                    new ReplayWriter().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                controller.display();
                controller.setFighting(true);
                Platform.runLater(new Runnable() {
                    public void run() {
                        controller.setLabel("HuluWa Win!");
                    }
                });
                break;
            }
            synchronized (Coordinate.class) {
                controller.display();
            }
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

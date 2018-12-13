package replay;

import battle.BattleField;
import gui.GameController;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;

public class Replay implements Runnable {
    private BufferedReader br;
    private GameController gc;
    private BattleField battle;
    public Replay(BufferedReader br, GameController gc) {
        this.br = br;
        this.gc = gc;
        this.battle = gc.getBattle();
    }
    public void run() {
        try {
            while(br.read()!=-1){
                String text = br.readLine();
                System.out.println(text);
                String[] array = text.split(" ");

                if (array[0].equals("ove") || array[0].equals("alk")) {
                    if (Integer.parseInt(array[1]) <= 7) {
                        System.out.println("hulu");
                        //System.out.println(array);
                        battle.huluBrothers.get(Integer.parseInt(array[1])).moveToReplay(battle.space, Integer.parseInt(array[2]), Integer.parseInt(array[3]));
                    } else {
                        System.out.println("demon");
                        battle.monsters.get(Integer.parseInt(array[1])).moveToReplay(battle.space, Integer.parseInt(array[2]), Integer.parseInt(array[3]));
                    }
                    gc.display();
                    TimeUnit.MILLISECONDS.sleep(200);
                } else if (array[0].equals("ttack")) {
                    boolean win = true;
                    if (array[4].equals("win")) {
                        win = true;
                    } else if (array[4].equals("lose")) {
                        win = false;
                    }
                    if (Integer.parseInt(array[1]) <= 7) {
                        battle.huluBrothers.get(Integer.parseInt(array[1])).attackReplay(battle.space,Integer.parseInt(array[2]), Integer.parseInt(array[3]), win, gc);
                    } else {
                        battle.monsters.get(Integer.parseInt(array[1])).attackReplay(battle.space,Integer.parseInt(array[2]), Integer.parseInt(array[3]), win, gc);
                    }
                    gc.display();
                    TimeUnit.MILLISECONDS.sleep(200);
                } else if (array[0].equals("lean")) {
                    System.out.println(array[1] + " " + array[2]);
                    battle.space.cleanSpace(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
                    gc.display();
                    TimeUnit.MILLISECONDS.sleep(200);
                }
            }
            Platform.runLater(new Runnable() {
                public void run() {
                    gc.setLabel("end");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

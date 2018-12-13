package gui;

import battle.BattleField;
import creature.Creature;
import javafx.fxml.Initializable;
import javafx.event.*;

import formation.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import replay.Replay;
import replay.ReplayWriter;
import space.Coordinate;
import space.TwoDimensionSpace;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class GameController implements Initializable {
    private boolean fighting;
    private BattleField battle;
    private Image background;
    private int width;
    private int height;
    private Creature selected;
    private static Thread[] threadPool = new Thread[17];
    private CyclicBarrier barrier;
    private Formation HuluFormation;
    private Formation DemonFormation;
    private ArrayList<Coordinate> attack;
    @FXML private Pane pane;
    @FXML private Canvas canvas;
    @FXML private Button yanhangHulu;
    @FXML private Button changsheHulu;
    @FXML private Button fangmenHulu;
    @FXML private Button yulinHulu;
    @FXML private Button fengshiHulu;
    @FXML private Button heyiHulu;
    @FXML private Button hengeHulu;
    @FXML private Button yanyueHulu;
    @FXML private Button yanhangDemon;
    @FXML private Button changsheDemon;
    @FXML private Button fangmenDemon;
    @FXML private Button yulinDemon;
    @FXML private Button fengshiDemon;
    @FXML private Button heyiDemon;
    @FXML private Button hengeDemon;
    @FXML private Button yanyueDemon;
    @FXML private Label info;
    @FXML private ImageView head;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        width = 1080;
        height = 720;
        battle = new BattleField();
        background = new Image("image/Hulugu.png");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background, 0, 0, width, height);
        fighting = true;
        threadPool = new Thread[17];
        attack = new ArrayList<>();
    }
    public static void shutdown() {
        for (int i = 0; i < threadPool.length; i++) {
            if (threadPool[i] != null) {
                threadPool[i].stop();
            }
        }
    }
    public TwoDimensionSpace<Creature> getSpace() {
        return battle.space;
    }
    public BattleField getBattle() {
        return battle;
    }
    public CyclicBarrier getBarrier() {
        return barrier;
    }
    public void setLabel(String str) {
        info.setText(str);
    }
    public void display() {
        //System.out.println("display");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        try {
            gc.drawImage(background, 0, 0, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                if (battle.space.getCreature(i, j) != null) {
                    try {
                        gc.drawImage(battle.space.getCreature(i, j).getImage(), 72 * j, 72 * i, 80, 80);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            for (Coordinate c : attack) {
                if (c.getYy() == 0 && c.getXx() == 0) {
                    continue;
                }
                gc.drawImage(new Image("image/battle1.png"), 72 * c.getYy(), 72 * c.getXx(), 80, 80);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void display(double x, double y) {
        //attack.add(new Coordinate(x, y));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background, 0, 0, width, height);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                if (battle.space.getCreature(i, j) != null) {
                    gc.drawImage(battle.space.getCreature(i, j).getImage(), 72 * j, 72 * i, 80, 80);
                }
            }
        }
        gc.drawImage(new Image("image/battle1.png"), 72 * y, 72 *x, 80, 80);
    }
    public synchronized void addAttack(Coordinate coord) {
        attack.add(coord);
    }
    public synchronized void removeAttack(Coordinate coord) {
        attack.remove(coord);
    }
    @FXML private void yanhangHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new YanhangFormation(), battle.space, 8, 7, 0);
        //HuluFormation = new YanhangFormation();
        display();
    }
    @FXML private void changsheHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new ChangsheFormation(), battle.space, 2, 2, 0);
        //HuluFormation = new ChangsheFormation();
        display();
    }
    @FXML private void hengeHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new HengeFormation(), battle.space, 2, 2, 0);
        //HuluFormation = new HengeFormation();
        display();
    }
    @FXML private void fengshiHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new FengshiFormation(), battle.space, 4, 5, 0);
        display();
    }
    @FXML private void yulinHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new YulinFormation(), battle.space, 4, 5, 0);
        display();
    }
    @FXML private void heyiHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new HeyiFormation(), battle.space, 4, 2, 0);
        display();
    }
    @FXML private void fangmenHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new FangmenFormation(), battle.space, 4, 5, 0);
        display();
    }
    @FXML private void yanyueHuluOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.huluBrothers.generateFormation(new YanyueFormation(), battle.space, 4, 5, 0);
        display();
    }
    
    @FXML private void yanhangDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new YanhangFormation(), battle.space, 1, 7, 1);
        display();
    }
    @FXML private void changsheDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new ChangsheFormation(), battle.space, 1, 12, 1);
        display();
    }
    @FXML private void hengeDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new HengeFormation(), battle.space, 1, 12, 1);
        display();
    }
    @FXML private void fengshiDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new FengshiFormation(), battle.space, 4, 10, 1);
        display();
    }
    @FXML private void yulinDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new YulinFormation(), battle.space, 4, 9, 1);
        display();
    }
    @FXML private void heyiDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new HeyiFormation(), battle.space, 4, 11, 1);
        display();
    }
    @FXML private void fangmenDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new FangmenFormation(), battle.space, 4, 9, 1);
        display();
    }
    @FXML private void yanyueDemonOnAction(ActionEvent event) {
        if (!fighting) {
            return;
        }
        battle.monsters.generateFormation(new YanyueFormation(), battle.space, 4, 9, 1);
        display();
    }

    @FXML private void canvasClick(MouseEvent event) {
        /*int x = (int)event.getX() - 60;
        int y = (int)event.getY();
        x = x / 30;
        y = y / 30;

        if (battle.space.getCreature(y, x) != null) {
            selected = battle.space.getCreature(y, x);
            head.setImage(selected.getImage());
            info.setText(selected.getName());
        }*/
    }

    @FXML private void canvasDragDetect(MouseEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        x = x / 72;
        y = y / 72;
        if (battle.space.getCreature(y, x) != null) {
            selected = battle.space.getCreature(y, x);
            //head.setImage(selected.getImage());
            //info.setText(selected.getName());
        }
    }

    @FXML private void canvasDrag(MouseEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        x = x / 72;
        y = y / 72;
        if (selected != null) {
            selected.moveTo(battle.space, y, x);
        }
        display();
    }

    private void replay(BufferedReader br) {
        threadPool[0] = new Thread(new Replay(br, this));
        threadPool[0].start();
    }

    @FXML private void paneKeyboard(KeyEvent event) {
        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.SPACE && fighting) {
            fighting = false;
            info.setText("gaming");
            barrier = new CyclicBarrier(17);
            Creature.controller = this;
            try {
                new ReplayWriter().setOut(new FileWriter("log"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                for (int i = 0; i <= 7; i++) {
                    Creature c = battle.huluBrothers.get(i);
                    new ReplayWriter().write("move " + i + " " +c.getCoordinateX() + " " + c.getCoordinateY() + "\n");
                }
                for (int i = 8; i <= 16; i++) {
                    Creature c = battle.monsters.get(i);
                    new ReplayWriter().write("move " + i + " " + c.getCoordinateX() + " " + c.getCoordinateY() + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int k = 0;
            for (int i = 0; i < 7; i++) {
                threadPool[k] = new Thread(battle.huluBrothers.member[i]);
                threadPool[k].start();
                k++;
            }
            threadPool[k] = new Thread(battle.huluBrothers.observer);
            threadPool[k].start();
            k++;
            for (int i = 0; i < 8; i++) {
                threadPool[k] = new Thread(battle.monsters.member[i]);
                threadPool[k].start();
                k++;
            }
            threadPool[k] = new Thread(battle.monsters.observer);
            threadPool[k].start();
            k++;
            //battle.huluBrothers.member
        } else if (event.getCode() == KeyCode.R) {
            barrier.reset();
            shutdown();
            attack.clear();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(background, 0, 0, width, height);
            info.setText("");
            fighting = true;
            battle = new BattleField();
            Creature.refresh();
        } else if (event.getCode() == KeyCode.L) {
            shutdown();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(background, 0, 0, width, height);
            info.setText("replay");
            fighting = true;
            battle = new BattleField();
            Creature.refresh();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(pane.getScene().getWindow());
            System.out.println(file.getPath());

            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file.getPath())); // 建立一个输入流对象reader
                BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                replay(br);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}

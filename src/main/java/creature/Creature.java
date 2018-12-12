package creature;
import annotation.AuthorAnno;
import gui.GameController;
import javafx.application.Platform;
import mythread.MyRunnable;
import space.*;
import javax.imageio.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import navigate.*;

import javafx.scene.image.*;

import static java.lang.Math.abs;

@AuthorAnno()
public class Creature implements Runnable{
    protected String name;
    protected int coordinateX;
    protected int coordinateY;
    protected String imgPath;
    protected Image image;
    protected boolean evil;
    protected boolean alive;

    public static GameController controller;
    public static int[] evilTest = {0,0,0,0,0,0,0,0,0};
    public static int[] justiceTest = {0,0,0,0,0,0,0,0};
    public static FileWriter out;
    protected int no;


    public Creature(){
        coordinateX = -1;
        coordinateY = -1;
        name = "未命名";
    }
    public Creature(String name){
        coordinateX = -1;
        coordinateY = -1;
        this.name = name;
    }
    public static void refresh() {
        for (int i = 0; i < evilTest.length; i++) {
            evilTest[i] = 0;
        }
        for (int i = 0; i < justiceTest.length; i++) {
            justiceTest[i] = 0;
        }
    }
    public void display() {
        controller.display();
    }
    public void loadImage() {
        image = new Image(imgPath);
    }
    public void setImage(String path) {
        imgPath = path;
    }
    public void setNo(int i) {
        this.no = i;
    }
    public boolean isEvil() {
        return evil;
    }

    public boolean isAlive() {
        return alive;
    }
    public String getPath() { return imgPath; }
    public Image getImage() {return image; }
    public String getName(){
        return name;
    }
    public void shoutOutName(){
        System.out.println(name);
    }
    public int getCoordinateX(){
        return coordinateX;
    }
    public int getCoordinateY(){
        return coordinateY;
    }
    public void setCoordinate(int x, int y){
        coordinateX = x;
        coordinateY = y;
    }
    public void die() {
        alive = false;
        imgPath = "image/die.png";
        image = new Image(imgPath);
    }
    public boolean moveTo(TwoDimensionSpace space, int x, int y){
        try {
            out.write("walk " + no + " " + x + " " + y + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!space.isExceed(x, y)){
            if (space.isEmpty(x, y)) {
                space.cleanSpace(coordinateX, coordinateY);
                space.setSpace(x, y, this);
                coordinateX = x;
                coordinateY = y;
                return true;
            }
        }
        return false;
    }
    public void moveToReplay(TwoDimensionSpace space, int x, int y){
        if (!space.isExceed(x, y)){
            if (space.isEmpty(x, y)) {
                space.cleanSpace(coordinateX, coordinateY);
                space.setSpace(x, y, this);
                coordinateX = x;
                coordinateY = y;
                System.out.println("move success");
            }
        }
    }
    public void removeFrom(TwoDimensionSpace space){
        if (!space.isExceed(coordinateX, coordinateY)){
            space.cleanSpace(coordinateX, coordinateY);
            coordinateX = -1;
            coordinateY = -1;
        }
    }

    public void walkPathTo(int i, int j, GameController gc) {
        TwoDimensionSpace space = gc.getSpace();
        HashMap<Integer, Integer> path = new Navigation().getNavigate(this, space);
        Stack<Integer> st = new Stack<Integer>();
        int x = i;
        int y = j;
        int z = 100*x+y;
        while (path.containsKey(z)) {
            st.push(z);
            z = path.get(z);
        }
        //System.out.println(st);
        int k = 1;
        while (!st.isEmpty() && k > 0) {
            int temp = st.pop();
            k--;
            moveTo(space, temp/100, temp%100);
            gc.display();
        }
        if (k == 1) {
            if (x > getCoordinateX() && space.isEmpty(getCoordinateX()+1, getCoordinateY())) {
                moveTo(space, getCoordinateX()+1, getCoordinateY());
            } else if (x < getCoordinateX() && space.isEmpty(getCoordinateX()-1, getCoordinateY())) {
                moveTo(space, getCoordinateX()-1, getCoordinateY());
            } else if (y > getCoordinateY() && space.isEmpty(getCoordinateX(), getCoordinateY()+1)) {
                moveTo(space, getCoordinateX(), getCoordinateY()+1);
            } else if (y < getCoordinateY() && space.isEmpty(getCoordinateX()+1, getCoordinateY()-1)) {
                moveTo(space, getCoordinateX(), getCoordinateY()-1);
            }
        }
    }
    public void forward(TwoDimensionSpace space, GameController gc) {
        int f = 1;
        if (isEvil()) {
            f = -1;
        }
        Random ra = new Random();
        int[] x = new int[8];
        int[] y = new int[8];
        x[0] = 0; x[1] = 1; x[2] = -1; x[3] = 0;
        y[0] = -1; y[1] = 0; y[2] = 0; y[3] = 1;
        x[4] = -1; x[5] = 1; x[6] = -1; x[7] = 1;
        y[4] = -1; y[5] = -1; y[6] = 1; y[7] = 1;

        for (int k = 0; k < 4; k++) {
            if (space.isCreatureOn(getCoordinateX()+x[k], getCoordinateY()+y[k])
                    && space.getCreature(getCoordinateX()+x[k], getCoordinateY()+y[k]).isEvil()^isEvil()
                    && space.getCreature(getCoordinateX()+x[k], getCoordinateY()+y[k]).isAlive()) {
                return;
            }
        }
        int trapped = 0;
        for (int k = 0; k < 4; k++) {
            if (space.isCreatureOn(getCoordinateX()+x[k], getCoordinateY()+y[k])
                    && !space.getCreature(getCoordinateX()+x[k], getCoordinateY()+y[k]).isAlive()) {
                trapped++;
            }
        }
        if (trapped == 4) {
            for (int k = 4; k < 8; k++) {
                if (space.isEmpty(getCoordinateX()+x[k], getCoordinateY()+y[k])) {
                    walkPathTo(getCoordinateX()+x[k], getCoordinateY()+y[k], gc);
                }
            }
        }
        int coord_x = 0;
        int coord_y = 0;
        int distance = 50;
        for (int i = 1; i < space.getSizeM()-1; i++) {
            for (int j = 0; j < space.getSizeN(); j++) {
                if (space.isEmpty(i, j)) {
                    for (int k = 0; k < 4; k++) {
                        if (space.isCreatureOn(i+x[k], j+y[k])
                                && space.getCreature(i+x[k], j+y[k]).isEvil()^isEvil()
                                && space.getCreature(i+x[k], j+y[k]).isAlive()) {
                            if (abs(getCoordinateX()-i) + abs(getCoordinateY()-j) <= distance) {
                                if ((ra.nextInt(5) >= 3 && distance == abs(getCoordinateX()-i) + abs(getCoordinateY()-j))
                                    || distance > abs(getCoordinateX()-i) + abs(getCoordinateY()-j)){
                                    coord_x = i;
                                    coord_y = j;
                                    distance = abs(getCoordinateX()-i) + abs(getCoordinateY()-j);
                                }
                            }

                        }
                    }
                }

            }
        }
        walkPathTo(coord_x, coord_y,  gc);
        return;
    }
    public int attack(TwoDimensionSpace space, GameController gc) {
        int[] x = new int[4];
        int[] y = new int[4];
        x[0] = 0; x[1] = 1; x[2] = -1; x[3] = 0;
        y[0] = -1; y[1] = 0; y[2] = 0; y[3] = 1;

        synchronized (Creature.class) {
            for (int i = 0; i < 4; i++) {
                if (space.isCreatureOn(getCoordinateX() + x[i], getCoordinateY() + y[i])
                        && space.getCreature(getCoordinateX() + x[i], getCoordinateY() + y[i]).isAlive()
                        && space.getCreature(getCoordinateX() + x[i], getCoordinateY() + y[i]).isEvil() ^ isEvil()) {

                    Random ra = new Random();
                    gc.display(getCoordinateX() + 0.5 * x[i], getCoordinateY() + 0.5 * y[i]);
                    if (ra.nextBoolean()) {
                        space.getCreature(getCoordinateX() + x[i], getCoordinateY() + y[i]).die();
                        try {
                            int temp_x = (getCoordinateX() + x[i]);
                            int temp_y = (getCoordinateY() + y[i]);
                            out.write("attack " + no + " " + temp_x + " " + temp_y + " " + "win" + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        die();
                        try {
                            int temp_x = (getCoordinateX() + x[i]);
                            int temp_y = (getCoordinateY() + y[i]);
                            out.write("attack " + no + " " + temp_x + " " + temp_y + " " + "lose" + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return i;
                }
            }
        }
        return  -1;
    }

    public void attackReplay(TwoDimensionSpace space, int x, int y, Boolean win, GameController gc) {
         gc.display(((double) getCoordinateX()+x)/2, ((double)getCoordinateY()+y)/2);
         try {
             TimeUnit.MILLISECONDS.sleep(200);
         } catch (Exception e) {
                    ;
         }
         if (win) {
             space.getCreature(x, y).die();
         } else {
             die();
         }
         gc.display();
         try {
             TimeUnit.MILLISECONDS.sleep(200);
         } catch (Exception e) {
             ;
         }
    }

    public void run() {
        int i = 99;
        int last = 5 + new Random().nextInt(5);
        while (i >= 0) {
            i--;
            int s = 1;
            for (int k = 0; k < 9; k++) {
                s *= evilTest[k];
            }
            if (s == 1) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        controller.setLabel("Justice win");
                    }
                });
                try {
                    controller.getBarrier().await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            s = 1;
            for (int k = 0; k < 8; k++) {
                s *= justiceTest[k];
            }
            if (s == 1) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        controller.setLabel("Evil win");
                    }
                });
                try {
                    controller.getBarrier().await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            if (isAlive()) {
                synchronized (Creature.class) {
                    if (isAlive()) {
                        forward(controller.getSpace(), controller);
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(20) * 2 + 200);
                } catch (Exception e) {
                    ;
                }

                int direction = 0;
                synchronized (Creature.class) {
                    if (isAlive()) {
                        direction = attack(controller.getSpace(), controller);
                    }
                }
                if (direction != -1) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(40) * 5 + 200);
                    } catch (Exception e) {
                        ;
                    }
                    synchronized (Creature.class) {
                        display();
                    }
                }
            } else {
                last--;
                if (last < 0 && getCoordinateX() != -1 && getCoordinateY() != -1) {
                    try {
                        out.write("clean "+ getCoordinateX() + " " + getCoordinateY() + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    controller.getSpace().cleanSpace(getCoordinateX(), getCoordinateY());
                }
                if (isEvil()) {
                    evilTest[no - 8] = 1;
                } else {
                    justiceTest[no] = 1;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                ;
            }
            try {
                controller.getBarrier().await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



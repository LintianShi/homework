package replay;
import annotation.AuthorAnno;
import creature.*;
import gui.GameController;
import javafx.application.Platform;
import space.TwoDimensionSpace;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@AuthorAnno()
public class MyRunnable implements Runnable {
    private int no;
    private static boolean f = true;
    private GameController controller;
    private Creature creature;
    private TwoDimensionSpace<Creature> space;
    private Object waitObject;
    private CyclicBarrier barrier;
    public static int[] evil = {0,0,0,0,0,0,0,0,0};
    public static int[] justice = {0,0,0,0,0,0,0,0};
    public MyRunnable(int no, Object waitObject, CyclicBarrier barrier, Creature c, TwoDimensionSpace<Creature> s, GameController gui) {
        this.waitObject = waitObject;
        this.barrier = barrier;
        this.creature = c;
        this.space = s;
        this.controller = gui;
        this.no = no;
    }
    public void run() {
        int i = 36;
        while (i >= 0 && !Thread.currentThread().isInterrupted()) {
            i--;
            int s = 1;
            for (int k = 0; k < 9; k++) {
                s *= MyRunnable.evil[k];
            }
            if (s == 1) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        controller.setLabel("Justice win");
                    }
                });
                try {
                    this.barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            s = 1;
            for (int k = 0; k < 8; k++) {
                s *= MyRunnable.justice[k];
            }
            if (s == 1) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        controller.setLabel("Evil win");
                    }
                });
                try {
                    this.barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            synchronized (MyRunnable.class) {
                if (creature.isAlive()) {
                    creature.forward(space, controller);
                    creature.attack(space, controller);

                } else {
                    if (creature.isEvil()) {
                        MyRunnable.evil[no-8] = 1;
                    } else {
                        MyRunnable.justice[no] = 1;
                    }
                }
            }
            try {
                this.barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    public static void main(String[] args) {
        Object wait = new Object();
        CyclicBarrier b = new CyclicBarrier(5);
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            //new Thread(new MyRunnable(wait, b)).start();
        }
    }
}


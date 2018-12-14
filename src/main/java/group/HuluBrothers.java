package group;
import java.util.Random;

import annotation.AuthorAnno;
import creature.*;
import sort.Sort;
import space.TwoDimensionSpace;
import formation.*;
@AuthorAnno()
public class HuluBrothers implements Group{
    public HuluWa member[];
    public Creature observer;
    public HuluBrothers(){
        member = new HuluWa[7];
        for (int i = 0; i < 7; i++){
            member[i] = new HuluWa(i+1);
        }
        //Creature grandpa = new Creature("爷爷");
        observer = new Grandpa();
    }
    public Creature get(int i) {
        if (i >= 0 && i < 7) {
            return member[i];
        } else if (i == 7) {
            return observer;
        } else {
            return null;
        }
    }
    public void shoutBrothersName(){
        for (HuluWa h : member){
            h.shoutOutName();
            System.out.print(' ');
        }
        System.out.println();
    }
    public void shoutBrothersColor(){
        for (HuluWa h : member){
            h.shoutOutColor();
            System.out.print(' ');
        }
        System.out.println();
    }
    public void random(){
        Random rand = new Random();
        for (int i = 7; i >= 1; i--){
            int x = rand.nextInt(i);
            HuluWa temp = member[i-1];
            member[i-1] = member[x];
            member[x] = temp;
        }
    }
    public void initialize() {
        int i = 0;
        for (HuluWa h : member) {
            h.loadImage();
            h.setNo(i);
            i++;
        }
        observer.loadImage();
        observer.setNo(7);
    }
    public void sort(Sort sort) {
        sort.sort(member);
    }
    public void generateFormation(Formation formation, TwoDimensionSpace space, int x, int y, int direction)
    {
        formation.generateFormation(space, member, observer, x, y, direction);
    }
    public boolean isAllDead() {
        //boolean allDead = true;
        for (HuluWa h : member) {
            if (h.isAlive()) {
                return false;
            }
        }
        if (observer.isAlive()) {
            return false;
        }
        return true;
    }
}

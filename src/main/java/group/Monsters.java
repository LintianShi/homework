package group;
import annotation.AuthorAnno;
import creature.*;
import space.TwoDimensionSpace;
import formation.*;
@AuthorAnno()
public class Monsters implements Group {
    public Creature[] member;
    public Creature observer;
    public Monsters(int n) {
        member = new Creature[n+1];
        member[0] = new Scorpion();
        for (int i = 1; i <= n; i++){
            member[i] = new Demon();
        }
        observer = new Snake();
    }
    public Monsters(){
        ;
    }
    public void initialize() {
        for (int i = 2; i < 7; i++) {
            member[i].setImage("image/Demon"+i+".png");
            //member[i].setNo(i+7);
        }
        for (int i = 0; i < member.length; i++) {
            member[i].setNo(i+8);
        }
        for (Creature c : member) {
            c.loadImage();
        }
        observer.loadImage();
        observer.setNo(16);
    }
    public Creature get(int i) {
        if (i >= 8 && i < 16) {
            return member[i-8];
        } else if (i == 16) {
            return observer;
        } else {
            return null;
        }
    }
    public void generateFormation(Formation formation, TwoDimensionSpace space, int x, int y, int direction)
    {
        formation.generateFormation(space, member, observer, x, y, direction);
    }
}

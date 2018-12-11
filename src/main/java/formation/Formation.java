package formation;
import annotation.AuthorAnno;
import space.*;
import creature.*;
@AuthorAnno()
public abstract class Formation {
    public abstract void generateFormation(TwoDimensionSpace space, Creature beings[], Creature ob, int x, int y, int direction);
}

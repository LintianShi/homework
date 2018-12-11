package group;
import annotation.AuthorAnno;
import formation.*;
import space.TwoDimensionSpace;
@AuthorAnno()
public interface Group {
    public void generateFormation(Formation formation, TwoDimensionSpace space, int x, int y, int direction);
    public void initialize();
}

package formation;
import space.TwoDimensionSpace;
import creature.*;
public class YanhangFormation extends Formation {
    public void generateFormation(TwoDimensionSpace space, Creature beings[], Creature ob, int x, int y, int direction){
        for (Creature creature : beings) {
            creature.removeFrom(space);
        }
        ob.removeFrom(space);
        if (direction == 0) {
            ob.moveTo(space, 7, 0);
        }
        else {
            ob.moveTo(space, 7, space.getSizeN() - 1);
        }
        for (int i = 0; i < beings.length; i++) {
            if (direction == 0) {
                beings[i].moveTo(space, x - i, y - i);
            } else {
                beings[i].moveTo(space, x + i, y + i);
            }
        }
    }
}

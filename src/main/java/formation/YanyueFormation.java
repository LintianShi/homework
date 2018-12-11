package formation;

import creature.Creature;
import space.TwoDimensionSpace;

public class YanyueFormation extends Formation{
    public void generateFormation(TwoDimensionSpace space, Creature beings[], Creature ob, int x, int y, int direction){
        for (Creature creature : beings) {
            creature.removeFrom(space);
        }
        ob.removeFrom(space);
        if (direction == 0) {
            ob.moveTo(space, 5, 0);
        }
        else {
            ob.moveTo(space, 5, space.getSizeN() - 1);
        }
        if (direction == 0) {
            beings[0].moveTo(space, x, y);
            beings[1].moveTo(space, x-1, y);
            beings[2].moveTo(space, x+1, y);
            beings[3].moveTo(space, x-2, y-1);
            beings[4].moveTo(space, x+2, y-1);
            beings[5].moveTo(space, x-2, y-2);
            beings[6].moveTo(space, x+2, y-2);
        } else {
            beings[0].moveTo(space, x, y);
            beings[1].moveTo(space, x+1, y);
            beings[2].moveTo(space, x-1, y+1);
            beings[3].moveTo(space, x+2, y+1);
            beings[4].moveTo(space, x-2, y+2);
            beings[5].moveTo(space, x+3, y+2);
            beings[6].moveTo(space, x-2, y+3);
            beings[7].moveTo(space, x+3, y+3);
        }
        /*for (int i = 0; i < beings.length; i++) {
            beings[i].moveTo(space, x + i, y);
        }*/
    }
}

import static org.junit.Assert.*;

import creature.Creature;
import formation.ChangsheFormation;
import group.HuluBrothers;
import group.Monsters;
import org.junit.Test;
import space.TwoDimensionSpace;

public class FormationTest {
    @Test
    public void testChangsheFormation() {
        HuluBrothers huluBrothers = new HuluBrothers();
        TwoDimensionSpace<Creature> space = new TwoDimensionSpace<Creature>(15, 15);
        huluBrothers.generateFormation(new ChangsheFormation(), space, 2, 2, 0);
        for (int i = 2; i <= 8; i++) {
            assertEquals(space.isEmpty(i, 2), false);
            assertEquals(space.isEmpty(i, 3), true);
        }
    }
    public void testHeyiFormation() {
        //HuluBrothers huluBrothers = new HuluBrothers();
        Monsters monsters = new Monsters(7);
        TwoDimensionSpace<Creature> space = new TwoDimensionSpace<Creature>(15, 15);
        monsters.generateFormation(new ChangsheFormation(), space, 7, 13, 1);
        assertEquals(space.isEmpty(7, 13), false);
        assertEquals(space.isEmpty(6, 12), false);
        assertEquals(space.isEmpty(8, 12), false);
        assertEquals(space.isEmpty(5, 11), false);
        assertEquals(space.isEmpty(9, 11), false);
        assertEquals(space.isEmpty(4, 10), false);
        assertEquals(space.isEmpty(10, 10), false);
        assertEquals(space.isEmpty(3, 9), false);
    }
}

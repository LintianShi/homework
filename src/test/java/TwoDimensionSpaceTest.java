import creature.Creature;
import org.junit.Test;
import space.TwoDimensionSpace;

import static org.junit.Assert.*;
public class TwoDimensionSpaceTest {
    @Test
    public void testIsExceed() throws Exception {
        TwoDimensionSpace<Creature> space = new TwoDimensionSpace<Creature>(15, 15);
        assertEquals(false, space.isExceed(10,10));
        assertEquals(true, space.isExceed(100,10));

    }
    @Test
    public void testIsEmpty() throws Exception {
        TwoDimensionSpace<Creature> space = new TwoDimensionSpace<Creature>(15, 15);
        space.setSpace(3,3, new Creature());
        assertEquals(true, space.isEmpty(10,10));
        assertEquals(false, space.isEmpty(3,3));
        assertEquals(false, space.isEmpty(-3,3));

    }
}

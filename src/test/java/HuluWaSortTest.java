import creature.HuluWa;
import org.junit.Test;
import sort.*;

import static org.junit.Assert.*;

public class HuluWaSortTest {
    @Test
    public void testColorSort() {
        HuluWa[] test = new HuluWa[7];
        test[0] = new HuluWa(5);
        test[1] = new HuluWa(1);
        test[2] = new HuluWa(4);
        test[3] = new HuluWa(7);
        test[4] = new HuluWa(2);
        test[5] = new HuluWa(3);
        test[6] = new HuluWa(6);
        HuluWa[] res = new HuluWa[7];
        for (int i = 1; i <= 7; i++) {
            res[i-1] = new HuluWa(i);
        }
        new HuluWaColorSort().sort(test);
        for (int i = 0; i < 7; i++) {
            assertEquals(res[i].getColor(), test[i].getColor());
        }
    }
    @Test
    public void testBubbleSort() {
        HuluWa[] test = new HuluWa[7];
        test[0] = new HuluWa(5);
        test[1] = new HuluWa(1);
        test[2] = new HuluWa(4);
        test[3] = new HuluWa(7);
        test[4] = new HuluWa(2);
        test[5] = new HuluWa(3);
        test[6] = new HuluWa(6);
        HuluWa[] res = new HuluWa[7];
        for (int i = 1; i <= 7; i++) {
            res[i-1] = new HuluWa(i);
        }
        new HuluWaPrioritySort().sort(test);
        for (int i = 0; i < 7; i++) {
            assertEquals(res[i].getPriority(), test[i].getPriority());
        }
    }

}

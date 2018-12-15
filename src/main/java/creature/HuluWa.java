package creature;
import color.COLOR;

import javafx.scene.image.*;
import java.io.File;
import java.io.IOException;

public class HuluWa extends Creature {
    private int priority;
    private COLOR color;
    public HuluWa(int huluwaNumber) {
        super();
        switch (huluwaNumber) {
            case 1:
                //super("老大", "image/Dawa.png", false);
                name = "老大";
                priority = 1;
                color = COLOR.RED;
                imgPath = "image/Dawa.png";
                break;
            case 2:
                name = "老二";
                priority = 2;
                color = COLOR.ORANGE;
                imgPath = "image/Erwa.png";
                break;
            case 3:
                name = "老三";
                priority = 3;
                color = COLOR.YELLOW;
                imgPath = "image/Sanwa.png";
                break;
            case 4:
                name = "老四";
                priority = 4;
                color = COLOR.GREEN;
                imgPath = "image/Siwa.png";
                break;
            case 5:
                name = "老五";
                priority = 5;
                color = COLOR.CYAN;
                imgPath = "image/Wuwa.png";
                break;
            case 6:
                name = "老六";
                priority = 6;
                color = COLOR.BLUE;
                imgPath = "image/Liuwa.png";
                break;
            case 7:
                name = "老七";
                priority = 7;
                color = COLOR.PURPLE;
                imgPath = "image/Qiwa.png";
                break;
            default:
                System.out.println("error:there is only 7 HuluWas");
                break;
        }
    }
    public void shoutOutColor(){
        System.out.print(color.colorInChinese);
    }
    public int getPriority(){
        return priority;
    }
    public COLOR getColor(){
        return color;
    }
    public boolean comparePriority(HuluWa compareTarget){
        //if priority is smaller than compareTarget return true
        if (priority < compareTarget.getPriority()){
            return true;
        } else {
            return false;
        }
    }
    public int compareColor(HuluWa compareTarget){
        return color.ordinal() - compareTarget.getColor().ordinal();
    }
}

# Java Final Project
* 161220108 侍林天 <254656131@qq.com>
## 开发环境
* IntelliJ IDEA 2018.2.5 (Ultimate Edition)
* Java版本 1.8.0_181
* Maven 3.6.0
## 项目简介
* HuluWa World是一个葫芦娃与妖精对战的游戏。游戏开始前玩家可以布置阵型，游戏开始后葫芦娃与妖精自动进行战斗。
* 除了战斗功能外，还支持录像回看。
## 运行截图  
![image](ScreenShots/battle1.png)
## 运行说明
* 使用Maven对项目进行编译，打包。
* 运行target目录下HuluWa-1.0-SNAPSHOT.jar。
* 进入游戏后，可以通过两侧按钮和对单位鼠标拖拽的方式进行排兵布阵。
* 按SPACE键开始游戏。按R键重置游戏状态。按L键选择录像进行回放。
## 项目详情
### 目录
* /replay 存放了录像，以供回放使用。
* /ScreenShots 存放了README.md使用到的图片
* /src/main/java 项目源代码
* /src/main/resources 项目资源文件
* /src/test/java 单元测试代码
### 代码结构
#### package space
* class Tile是二维平面的基本组成单位，生物体能够放置在Tile上。Tile具有一系列接口可以判断该Tile对象上是否有生物体、将该Tile对象上的生物体移除、在该Tile上放置一个生物体等。  
```java
public class Tile<T extends Creature> {
    private int coordinateX;
    private int coordinateY;
    private T creatureStandOnTile;
    public void removeCreatureStandOnTile() {
        /*...*/
    }
    public void setCreatureStandOnTile(T x) {
        /*...*/
    }
    public boolean isEmpty() {
        /*...*/
    }
    public Creature getCreature() {
        /*...*/
    }
}
```
* class TwoDimensionSpace实现了一个由Tile组成的二维平面。利用Tile类提供的接口，可以对TwoDimensionSpace对象的某一个坐标进行操作。
```java
public class TwoDimensionSpace<T extends Creature> {
    private Tile<T> space[][];
    private int size;
    private int sizeM;
    private int sizeN;
    public boolean isEmpty(int x, int y) {
        /*...*/
    }
    public boolean isCreatureOn(int x, int y) {
        /*...*/
    }
    public Creature getCreature(int x, int y) {
        /*...*/
    }
    /*...*/
}
```
* class Coordinate具有两个成员，记录X轴坐标和Y轴坐标，用于生物体寻找路径等需要用到二元数对的地方记录坐标。
#### package creature
* class Creature是所有生物体的基类，能够在二维平面上行走战斗。并且实现了Runnable接口。
```java
public class Creature implements Runnable{
    /*...*/
}
```
* class Demon继承于Creature。
* class HuluWa继承于Creature。
* class Grandpa继承于Creature。
* class Snake继承于Demon。
* class Scorpion继承于Demon。
![image](ScreenShots/creature1.png)
#### package group
* interface Group是一个公共接口，实现该接口的类应能够进行排列阵型。
```java
public interface Group {
    public void generateFormation(Formation formation, TwoDimensionSpace space, int x, int y, int direction);
    public void initialize();
}
```
* class HuluBrothers实现了Group接口，并由一组HuluWa对象和一个Creature对象组合而成。
* class Monsters实现了Group接口，并由一组Creature对象组合而成。

package space;
import annotation.AuthorAnno;
import creature.*;
@AuthorAnno()
public class TwoDimensionSpace<T extends Creature> {
    private Tile<T> space[][];
    private int size;
    private int sizeM;
    private int sizeN;
    public TwoDimensionSpace(int N){
        size = N;
        space = new Tile[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                space[i][j] = new Tile(i, j);
            }
        }
    }
    public TwoDimensionSpace(int M, int N){
        sizeM = M;
        sizeN = N;
        space = new Tile[M][N];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                space[i][j] = new Tile(i, j);
            }
        }
    }
    /*public int size() {
        return size;
    }*/

    public int getSizeM() {
        return sizeM;
    }

    public int getSizeN() {
        return sizeN;
    }

    public boolean isEmpty(int x, int y){
        if (!isExceed(x, y)){
            return space[x][y].isEmpty();
        }
        return false;
    }
    public boolean isCreatureOn(int x, int y) {
        if (!isExceed(x, y) && getCreature(x, y) != null) {
            return true;
        }
        return false;
    }
    public void cleanSpace(int x, int y){
        if (!isExceed(x, y)){
            space[x][y].removeCreatureStandOnTile();
        }
    }
    public boolean isExceed(int x, int y){
        if (x < sizeM && y < sizeN && x >= 0 && y>= 0){
            return false;
        } else {
            return true;
        }
    }
    public void setSpace(int x, int y, T p){
        if (!isExceed(x,y)){
            space[x][y].setCreatureStandOnTile(p);
        }
    }
    public void displaySpace(){
        System.out.println();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (space[i][j].isEmpty()){
                    System.out.print(" \t@\t");
                } else {
                    System.out.print(space[i][j].getCreatureName()+"\t");
                }
            }
            System.out.println();
        }
    }

    public Creature getCreature(int i, int j) {
        if (!isExceed(i, j)) {
            return space[i][j].getCreature();
        }
        return null;
    }
}

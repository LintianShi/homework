package space;

public class Coordinate {
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(double xx, double yy) {
        this.xx = xx;
        this.yy = yy;
    }
    public Coordinate(int c) {
        this.x = c / 100;
        this.y = c % 100;
    }
    private double xx;
    private double yy;
    private int x;
    private int y;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public double getXx() {
        return xx;
    }

    public double getYy() {
        return yy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate c = (Coordinate) o;
        if (this.x == c.getX() && this.y == c.getY()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return x*100 + y;
    }

}

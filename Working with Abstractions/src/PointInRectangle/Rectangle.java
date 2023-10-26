package PointInRectangle;

public class Rectangle {
    private int bottomLeftX;
    private int bottomLeftY;
    private int topRightX;
    private int topRightY;

    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.topRightX = topRightX;
        this.topRightY = topRightY;
    }

    public boolean contains(Point point){
        return point.getXCoordinate()>=bottomLeftX && point.getXCoordinate()<=topRightX
                && point.getYCoordinate()>=bottomLeftY && point.getYCoordinate()<=topRightY;
    }
}

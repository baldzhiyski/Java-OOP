package solidLab.p03_LiskovSubstitution.p01_Square;

public class Rectangle extends Shape {
   private double width;

   private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return width*height;
    }
}

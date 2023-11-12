package shapes;

public class Circle implements Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    public void calculatePerimeter() {
        System.out.println(2*Math.PI*radius);
    }

    @Override
    public void calculateArea() {
        System.out.println(Math.PI*Math.pow(radius,2));
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}

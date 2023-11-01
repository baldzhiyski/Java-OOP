package ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setHeight(double height) {
        if(height<=0){
            throw new IllegalArgumentException("Height cannot be zero or negative") ;
        }
        this.height = height;
    }

    private void setLength(double length) {
        if(length<=0){
            throw new IllegalArgumentException("Length cannot be zero or negative") ;
        }
        this.length = length;
    }

    private void setWidth(double width) {
        if(width<=0){
            throw new IllegalArgumentException("Width cannot be zero or negative") ;
        }
        this.width = width;
    }
    public double calculateSurfaceArea() {
        return 2*(length*width + length*height + width*height);
    }
    public double calculateLateralSurfaceArea() {
        return 2*(length*height + width*height);
    }
    public double calculateVolume() {
        return width*length*height;
    }

    @Override
    public String toString() {
        return String.format("Surface Area - %.2f\nLateral Surface Area - %.2f\nVolume - %.2f\n"
        ,calculateSurfaceArea(),calculateLateralSurfaceArea(),calculateVolume());
    }
}

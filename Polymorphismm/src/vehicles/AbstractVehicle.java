package vehicles;

import java.text.DecimalFormat;

public abstract class AbstractVehicle implements Vehicle{
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public AbstractVehicle(double fuelQuantity, double fuelConsumption,double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    @Override
    public void refuel(double liters){
        if(liters<=0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if(this.fuelQuantity+liters>tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.fuelQuantity= this.fuelQuantity+liters;
    }
    @Override
    public String drive(double distance){
        double neededFuel = this.fuelConsumption * distance;
        String result = String.format("%s needs refueling", this.getClass().getSimpleName());

        if (this.fuelQuantity >= neededFuel) {
            result = String.format("%s travelled %s km",
                    this.getClass().getSimpleName(),
                    df.format(distance));

            this.fuelQuantity -= neededFuel;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(),this.fuelQuantity);
    }
}

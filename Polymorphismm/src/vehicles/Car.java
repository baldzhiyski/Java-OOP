package vehicles;

public class Car extends AbstractVehicle{
    public Car(double fuelQuantity, double fuelConsumption,double tankCapacity) {
        super(fuelQuantity, fuelConsumption,tankCapacity);
        super.setFuelConsumption(fuelConsumption+0.9);
    }
}

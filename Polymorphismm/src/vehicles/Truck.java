package vehicles;

public class Truck extends AbstractVehicle{
    public Truck(double fuelQuantity, double fuelConsumption,double tankCapacity) {
        super(fuelQuantity, fuelConsumption,tankCapacity);
        super.setFuelConsumption(fuelConsumption+1.6);
    }

    @Override
    public void refuel(double liters) {
       super.refuel(liters*0.95);
    }
}

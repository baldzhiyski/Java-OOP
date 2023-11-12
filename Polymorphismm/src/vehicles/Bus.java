package vehicles;

public class Bus extends AbstractVehicle{
    public static final boolean DEFAULT_IS_EMPTY = true;
    private boolean isEmpty;
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.isEmpty=DEFAULT_IS_EMPTY;
    }
    @Override
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    public void turnOnOfAC(boolean isEmpty) {
        if(isEmpty){
            super.setFuelConsumption(getFuelConsumption());
        }else{
            super.setFuelConsumption(getFuelConsumption()+1.4);
        }
    }

    @Override
    public String drive(double distance) {
        return super.drive(distance);
    }
}

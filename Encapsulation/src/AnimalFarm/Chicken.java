package AnimalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }

    private void setAge(int age) {
        if(age<0 || age>15){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age=age;
    }

    private void setName(String name) {
        if(name.isBlank() || name==null){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name=name;
    }
    public double productPerDay(){
        return calculateProductPerDay();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private double calculateProductPerDay() {
        double product = 0.75;
        if(age<6){
            product=2;
        }else if(age<12){
            product=1;
        }
        return product;
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce 1.00 eggs per day.",
                name,age);
    }
}


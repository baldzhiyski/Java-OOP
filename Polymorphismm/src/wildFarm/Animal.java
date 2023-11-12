package wildFarm;

import java.text.DecimalFormat;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten;
    private String livingRegion;

    protected Animal(String animalName, String animalType, Double animalWeight,String livingRegion) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten=0;
        this.livingRegion=livingRegion;
    }

    public abstract void makeSound();
    public abstract boolean canEat(Food food);
    public void eat(Food food){
        if(canEat(food)){
            foodEaten+=food.getQuantity();
        }else{
            System.out.printf("%ss are not eating that type of food!\n",
                    getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]",
                getClass().getSimpleName(),
                animalName,
                decimalFormat.format(animalWeight),
                livingRegion,
                foodEaten
        );
    }
}

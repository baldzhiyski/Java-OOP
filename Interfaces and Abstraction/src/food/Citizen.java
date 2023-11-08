package food;

public class Citizen implements Buyer,Person, Identifiable {
    private String name;
    private int age;
    private String id;
    private String birthDay;
    private int food;

    public Citizen(String name, int age, String id, String birthDay) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDay = birthDay;
        this.food = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getBirthDay() {
        return birthDay;
    }

    @Override
    public void buyFood() {
        this.food +=10;
    }

    @Override
    public int getFood() {
        return food;
    }
}

package animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    public String produceSound() {
        return "";
    }

    public void setName(String name) {
        checkIfValid(name);
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public void setGender(String gender) {
        checkIfValid(gender);
        this.gender = gender;
    }

    private void checkIfValid(String word) {
        if (word.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return String.format("%s\n" +
                        "%s %d %s\n" +
                        "%s", this.getClass().getSimpleName(),
                this.name, this.age, this.gender, this.produceSound());
    }
}

package StudentEx;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%s is %d years old.",name,age) + getCommentary();
    }

    private String getCommentary() {
        if(grade>=5){
            return " Excellent student.";
        }else if(grade<5.00 && grade>=3.50){
            return " Average student.";
        }
        return " Very nice person.";
    }
}

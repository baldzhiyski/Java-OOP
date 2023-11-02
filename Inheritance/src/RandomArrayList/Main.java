package RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList<Integer> randomArrayList = new RandomArrayList<Integer>();
        randomArrayList.add(12);
        randomArrayList.add(13);
        Integer randomElement = randomArrayList.getRandomElement();
        System.out.println(randomElement);
    }
}

package telephony;

import java.util.List;

public class Smartphone implements Browsable,Callable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder result = new StringBuilder();
        for (String url : urls) {
            if(!containsDigits(url)) {
                result.append("Browsing: ").append(url).append("!");
            }else{
                result.append("Invalid URL!");
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    private boolean containsDigits(String url) {
        for (char symbol : url.toCharArray()) {
            if(Character.isDigit(symbol)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String call() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            if (isValidNumber(numbers.get(i))) {
                result.append("Calling... ").append(numbers.get(i));
                if (i != numbers.size() - 1) {
                    result.append(System.lineSeparator());
                }
            }else{
                result.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    private boolean isValidNumber(String number) {
        for (char symbol : number.toCharArray()) {
            if(!Character.isDigit(symbol)){
                return false;
            }
        }
        return true;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public List<String> getUrls() {
        return urls;
    }
}

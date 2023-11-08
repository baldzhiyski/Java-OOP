package hierarchy;

public class AddCollection extends Collection implements Addable{
    @Override
    public int add(String string) {
        super.addItems(string);
        return super.getItems().size()-1;
    }
}

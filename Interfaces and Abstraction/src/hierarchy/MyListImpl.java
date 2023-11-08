package hierarchy;

public class MyListImpl extends Collection implements MyList{
    @Override
    public String remove() {
        return super.removeFirst();
    }

    @Override
    public int add(String string) {
        super.addFirst(string);
        return 0;
    }

    @Override
    public int getUsed() {
        return super.getItems().size();
    }
}

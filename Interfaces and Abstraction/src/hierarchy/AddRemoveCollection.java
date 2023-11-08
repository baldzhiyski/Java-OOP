package hierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public String remove() {
        return super.removeLast();
    }

    @Override
    public int add(String string) {
        super.addFirst(string);
        return 0;
    }
}

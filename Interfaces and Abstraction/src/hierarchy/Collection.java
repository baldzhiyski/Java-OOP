package hierarchy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Collection {
    private int maxSize;
    private List<String> items;

    public Collection() {
        items=new ArrayList<>();
        maxSize=100;
    }


    public void addItems(String item){
        this.items.add(item);
    }
    public void addFirst(String item){
        this.items.add(0,item);
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }
    public String removeFirst(){
        return this.items.remove(0);
    }

    public int getMaxSize() {
        return maxSize;
    }
    public String removeLast(){
        return this.items.remove(this.items.size()-1);
    }
}

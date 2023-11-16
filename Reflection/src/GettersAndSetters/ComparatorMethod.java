package GettersAndSetters;

import java.lang.reflect.Method;
import java.util.Comparator;

public class ComparatorMethod implements Comparator<Method> {
    @Override
    public int compare(Method first, Method second) {

        return first.getName().compareTo(second.getName());

    }
}

package GettersAndSetters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Reflection> clazz = Reflection.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();

        Arrays.stream(declaredMethods)
                .filter(m->!m.getName().equals("toString"))
                .sorted(new ComparatorMethod())
                .forEach(m-> System.out.println(formatMethodInfo(m)));


//        System.out.println(clazz);
//        System.out.println(clazz.getSuperclass());
//
//        Class<?>[] interfaces = clazz.getInterfaces();
//        for (Class<?> i : interfaces) {
//            System.out.println(i);
//        }
//        Field webAddress = clazz.getDeclaredField("webAddress");
//        webAddress.setAccessible(true);
//
//        Constructor<GettersAndSetters.Reflection> constructor = clazz.getConstructor();
//        GettersAndSetters.Reflection reflection = constructor.newInstance();
//
//        System.out.println(reflection);


    }
    public static String formatMethodInfo (Method m){
        if(m.getName().startsWith("get")){
            return String.format("%s will return class %s",m.getName(),m.getReturnType().getName());
        }
        return String.format("%s and will set field of class %s",m.getName(),m.getParameterTypes()[0].getName());
    }
}
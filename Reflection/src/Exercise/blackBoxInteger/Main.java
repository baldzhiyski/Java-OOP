package Exercise.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<BlackBoxInt> blackBoxIntClass = BlackBoxInt.class;
        Constructor<BlackBoxInt> con = blackBoxIntClass.getDeclaredConstructor();
        con.setAccessible(true);
        BlackBoxInt blackBox = con.newInstance();
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        
        while (!line.equalsIgnoreCase("end")){
            String[] tokens = line.split("_");
            String methodName = tokens[0];
            int parameter = Integer.parseInt(tokens[1]);

            Method declaredMethod = blackBoxIntClass.getDeclaredMethod(methodName, int.class);
            declaredMethod.setAccessible(true);
                declaredMethod.invoke(blackBox,parameter);

            Field innerValue = blackBoxIntClass.getDeclaredField("innerValue");
            innerValue.setAccessible(true);
            System.out.println(innerValue.get(blackBox));
            line=scan.nextLine();
        }
    }
}
 
package Exercise.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;
        Field[] declaredFields = richSoilLandClass.getDeclaredFields();
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();

		Consumer<Field> printFields = field ->System.out.printf("%s %s %s\n",
				Modifier.toString(field.getModifiers()),
				field.getType().getSimpleName(),field.getName());

        while (!line.equals("HARVEST")) {
            switch (line) {
                case "all":
					Arrays.stream(declaredFields)
							.forEach(printFields);
                    break;
                default:
                    String finalLine = line;
                    Arrays.stream(declaredFields)
                            .filter(field -> Modifier.toString(field.getModifiers()).equals(finalLine))
                            .forEach(printFields);
            }
            line = scan.nextLine();
        }
    }
}

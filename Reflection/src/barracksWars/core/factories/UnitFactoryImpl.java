package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
		// TODO: implement for problem 3

		try {
			Class clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<Unit> constructor = clazz.getDeclaredConstructor();

			return  constructor.newInstance();
		} catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException |
				 IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

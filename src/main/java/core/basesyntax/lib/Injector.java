package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.NoSuchDaoFoundException;
import core.basesyntax.factory.Factory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Injector {
    public static Object getInstance(Class<?> clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Object value = null;
                if (BetDao.class.equals(field.getType())) {
                    value = Factory.getBetDao();
                } else if (UserDao.class.equals(field.getType())) {
                    value = Factory.getUserDao();
                }
                if (Objects.requireNonNull(value).getClass().isAnnotationPresent(Dao.class)) {
                    field.setAccessible(true);
                    field.set(instance, value);
                } else {
                    throw new NoSuchDaoFoundException("Expected type must contain @Dao annotation");
                }
            }
        }
        return instance;
    }
}

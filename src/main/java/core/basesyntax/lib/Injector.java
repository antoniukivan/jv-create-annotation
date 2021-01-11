package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.NoSuchDaoFoundException;
import core.basesyntax.factory.BetDaoImplFactory;
import core.basesyntax.factory.UserDaoImplFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Injector {
    public static Object getInstance(Class<?> clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Inject.class) != null) {
                Object value = null;
                if (BetDao.class.equals(field.getType())) {
                    value = BetDaoImplFactory.getBetDao();
                } else if (UserDao.class.equals(field.getType())) {
                    value = UserDaoImplFactory.getUserDao();
                }
                if (Objects.requireNonNull(value).getClass().isAnnotationPresent(Dao.class)) {
                    field.setAccessible(true);
                    field.set(instance, value);
                } else {
                    throw new NoSuchDaoFoundException("Invalid type: " + field.getType());
                }
            }
        }
        return instance;
    }
}

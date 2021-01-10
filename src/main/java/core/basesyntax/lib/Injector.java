package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.factory.BetDaoImplFactory;
import core.basesyntax.factory.UserDaoImplFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {
    public static Object getInstance(Class clazz) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        Object instance = clazz.getDeclaredConstructor().newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Inject.class) != null
                    && field.getType().isAnnotationPresent(Dao.class)) {
                field.setAccessible(true);
                if (BetDao.class.equals(field.getType())) {
                    field.set(instance, BetDaoImplFactory.getBetDao());
                } else if (UserDao.class.equals(field.getType())) {
                    field.set(instance, UserDaoImplFactory.getUserDao());
                }
            }
        }
        return instance;
    }
}

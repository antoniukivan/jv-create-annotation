package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.factory.BetDaoImplFactory;

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
                switch (field.getType()) {
                    case BetDao.class:
                        field.set(instance, BetDaoImplFactory.getBetDao());
                    case UserDao.class:
                        field.set(instance, UserDa);
                }
            }
        }
        return instance;
    }
}

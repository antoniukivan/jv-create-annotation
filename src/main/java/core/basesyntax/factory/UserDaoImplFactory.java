package core.basesyntax.factory;

import core.basesyntax.dao.UserDao;
import core.basesyntax.dao.UserDaoImpl;

public class UserDaoImplFactory {
    private static UserDao userDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
}

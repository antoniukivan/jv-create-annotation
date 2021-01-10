package core.basesyntax.dao;

import core.basesyntax.lib.Dao;
import core.basesyntax.model.User;
import java.util.List;

@Dao
public interface UserDao {
    void add(User user);

    List<User> getAll();
}

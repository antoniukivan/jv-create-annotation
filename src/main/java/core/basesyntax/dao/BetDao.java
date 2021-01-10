package core.basesyntax.dao;

import core.basesyntax.lib.Dao;
import core.basesyntax.model.Bet;
import java.util.List;

@Dao
public interface BetDao {
    void add(Bet bet);

    List<Bet> getAll();
}

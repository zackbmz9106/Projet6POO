package magasin;

import database.QueryDB;
import database.Transaction;

public interface IdbInterface {

    void update(Transaction tx, String[] nomsDeChampsAMettreAjour);

    void load(Transaction transaction, long id);

    void create(Transaction tx);

    void query(Transaction tx, QueryDB qDB);

    void delete(Transaction tx);
}

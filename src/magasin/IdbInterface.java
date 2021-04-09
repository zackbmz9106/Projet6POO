package magasin;

import database.QueryDB;
import database.Transaction;

import java.util.ArrayList;

public interface IdbInterface {

    void update(Transaction tx, String[] nomsDeChampsAMettreAjour);

    void load(Transaction tx, int id);

    void create(Transaction tx);

    //TODO : add operand
    ArrayList<Long> query(Transaction tx, QueryDB qDB);

    void delete(Transaction tx);
}

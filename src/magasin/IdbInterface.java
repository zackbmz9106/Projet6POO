package magasin;

import database.Transaction;

import java.util.ArrayList;

public interface IdbInterface {

    void update(Transaction tx, String[] nomsDeChampsAMettreAjour);

    void load(Transaction tx, int id);

    void create(Transaction tx);

    //TODO : add operand
    ArrayList<Long> query(Transaction tx);

    void delete(Transaction tx);
}

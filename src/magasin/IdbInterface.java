package magasin;

import database.CObjTransaction;

import java.util.ArrayList;

public interface IdbInterface {

    boolean update(CObjTransaction objt, String[] nomsDeChampsAMettreAjour);

    boolean load(CObjTransaction objt, int id);

    boolean create(CObjTransaction objt);

    //TODO : add operand
    ArrayList<Long> query(CObjTransaction objt);

    boolean delete(CObjTransaction objt, int id);
}

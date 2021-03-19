package database;

import java.util.ArrayList;

public interface IdbInterface {

    boolean update(CObjTransaction objt, String[] nomsDeChampsAMettreAjour);

    boolean load(CObjTransaction objt, int id);

    boolean create(CObjTransaction objt);

    ArrayList<Long> query(CObjTransaction objt);

    boolean delete(CObjTransaction objt, int id);
}

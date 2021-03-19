package database;

import java.util.ArrayList;

public interface IdbInterface {

    boolean update(CObjTransaction Cobjt, String[] nomsDeChampsAMettreAjour);

    boolean load(CObjTransaction Cobjt, int id);

    boolean create(CObjTransaction Cobjt);

    ArrayList<Long> query(CObjTransaction Cobjt);

    boolean delete(CObjTransaction Cobjt,int id);
}

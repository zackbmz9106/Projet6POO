package commons;

public class Adresse {
    private  String voie ;
    private int nVoie;
    private  int codePostal;

    public Adresse(String voie, int nVoie, int codePostal) {
        this.voie = voie;
        this.nVoie = nVoie;
        this.codePostal = codePostal;
    }
    @Override
    public String toString() {
        return this.nVoie  + this.voie  + this.codePostal;
    }

    public String toDB(){
        return this.nVoie + ";" + this.voie + ";"+ this.codePostal;
    }
    public void fromDB(String in){
        String[] split = in.split(";");
        this.nVoie = Integer.parseInt(split[0]);
        this.voie = split[1];
        this.codePostal = Integer.parseInt(split[2]);

    }
}

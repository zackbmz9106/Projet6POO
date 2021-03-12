package commons;

public class Date {
	private int jour;
	private int mois;
	private int annee;

	public Date(int jour, int mois, int annee) {
		if(this.verifyDate(jour, mois, annee)){
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		}else {
			this.jour = 1;
			this.mois = 1 ;
			this.annee = 1970;
		}
	}

	public String toString() {
		return this.jour + "/" + this.mois + "/" + this.annee;
	}
	public int getJour() {
		return this.jour;
	}
	public int getMois() {
		return this.mois;
	}
	public int getAnnee() {
		return this.annee;
	}

	private int getMoisJours(int mois, int annee) {
		if (mois == 2) {
			if (annee % 4 == 0 && annee % 400 == 0 && annee % 100 == 0) {
				return 29;
			} else {
				return 28;
			}
		}
		return (mois % 2 == 0) ? 30 : 31;
	}

	private boolean verifyDate(int jour, int mois, int annee) {
		if (annee >= 1970) {
			if (mois <= 12) {
				if (this.getMoisJours(jour, annee) == mois) {
					return true;
				}
			}
		}
		return false;
	}
	
}

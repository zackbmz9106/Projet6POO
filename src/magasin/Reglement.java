package magasin;

import commons.Date;

public class Reglement {
	private Client infoClient;
	private boolean isCredit;
	private boolean isDiffere;
	private Date dateDiffere;
	
	public Reglement(Client infoClient,boolean isCredit,boolean isDiffere,Date dateDiffere) {
		this.infoClient = infoClient;
		this.isCredit = isCredit;
		this.isDiffere = isDiffere;
		this.dateDiffere = dateDiffere;
	}
	
}

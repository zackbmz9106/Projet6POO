package magasin;


import java.util.Date;

public class Reglement {
	private final Client infoClient;
	private final boolean isCredit;
	private final boolean isDiffere;
	private final Date dateDiffere;
	
	public Reglement(Client infoClient,boolean isCredit,boolean isDiffere,Date dateDiffere) {
		this.infoClient = infoClient;
		this.isCredit = isCredit;
		this.isDiffere = isDiffere;
		this.dateDiffere = dateDiffere;
	}
	
}

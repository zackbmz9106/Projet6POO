package logic;

public class ApplicationEvent {

    public enum appWindows {
        CREATE_CLIENT,
        QUERRY_CLIENT,
        CREATE_PRODUIT, CREATE_EMPLOYE,
        CREATE_CLIENT_QUERY,
        CREATE_CLIENT_CHART, CREATE_COMMANDE
    }

    public enum events {
        SHOW_WINDOW, NEW_CLIENT, NEW_PRODUIT, NEW_EMPLOYE, DELETED,
    }
}

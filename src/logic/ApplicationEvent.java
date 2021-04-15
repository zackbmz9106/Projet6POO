package logic;

public class ApplicationEvent {

    public enum appWindows {
        CREATE_CLIENT,
        QUERRY_CLIENT,
        CREATE_PRODUIT, CREATE_EMPLOYE, CREATE_COMMANDE
    }

    public enum events {
        SHOW_WINDOW, NEW_CLIENT, NEW_PRODUIT, NEW_EMPLOYE,
    }
}

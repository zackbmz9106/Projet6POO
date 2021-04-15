package logic;

import magasin.Client;
import magasin.Employe;
import magasin.Produit;

import java.util.ArrayList;
import java.util.List;

public class ApplicationEventDispatcher {

    private final List<IApplicationEventDispatcherListener> listeners = new ArrayList<>();

    public void addListener(IApplicationEventDispatcherListener itf) {
        listeners.add(itf);
    }

    public void showWindow(ApplicationEvent.appWindows window, boolean bShow) {
        listeners.forEach((l) -> {
            l.dispatchEvent(ApplicationEvent.events.SHOW_WINDOW, window, bShow);
        });
    }

    public void notifyNewClient(Client c) {
        listeners.forEach((l) -> {
            l.dispatchEvent(ApplicationEvent.events.NEW_CLIENT, c);
        });
    }

    public void notifyNewProduit(Produit p) {
        listeners.forEach((l) -> {
            l.dispatchEvent(ApplicationEvent.events.NEW_PRODUIT, p);
        });
    }

    public void notifyNewEmploye(Employe e) {
        listeners.forEach((l) -> {
            l.dispatchEvent(ApplicationEvent.events.NEW_EMPLOYE, e);
        });
    }
}

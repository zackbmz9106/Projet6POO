package logic;

import magasin.Client;

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
    public void notifyNewClient(Client c ){
        listeners.forEach((l) -> {
            l.dispatchEvent(ApplicationEvent.events.NEW_CLIENT, c);
        });
    }
}

package logic;

public interface IApplicationEventDispatcherListener {
    void dispatchEvent(ApplicationEvent.events event, Object... params);
}

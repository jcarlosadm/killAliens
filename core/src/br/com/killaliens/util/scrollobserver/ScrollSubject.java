package br.com.killaliens.util.scrollobserver;

public interface ScrollSubject {
    
    public void registerScrollObserver(ScrollObserver observer);
    
    public void removeScrollObserver(ScrollObserver observer);
    
    public void notifyScrollObservers(float deltaX, float deltaY);
}

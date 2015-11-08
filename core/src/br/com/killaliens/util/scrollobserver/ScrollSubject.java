package br.com.killaliens.util.scrollobserver;

public interface ScrollSubject {
    
    /**
     * Register a scroll observer
     * @param observer
     */
    public void registerScrollObserver(ScrollObserver observer);
    
    /**
     * @param observer remove a scroll observer
     */
    public void removeScrollObserver(ScrollObserver observer);
    
    /**
     * notify scroll observers
     * @param deltaX
     * @param deltaY
     */
    public void notifyScrollObservers(float deltaX, float deltaY);
}

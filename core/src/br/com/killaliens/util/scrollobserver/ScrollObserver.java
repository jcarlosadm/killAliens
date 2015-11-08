package br.com.killaliens.util.scrollobserver;

/**
 * ScrollObserver
 * TODO Observer pattern
 */
public interface ScrollObserver {
    
    /**
     * update scroll
     * @param xDelta
     * @param yDelta
     */
    public void updateScroll(float xDelta, float yDelta);
}

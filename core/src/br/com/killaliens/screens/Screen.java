package br.com.killaliens.screens;

public interface Screen {
    public void act();
    public void draw();
    public void resize(int width, int height);
}

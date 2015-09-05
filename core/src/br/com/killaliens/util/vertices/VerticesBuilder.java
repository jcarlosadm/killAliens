package br.com.killaliens.util.vertices;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class VerticesBuilder {
    
    private List<Vector2> points = new ArrayList<Vector2>();
    
    public void addPoint(float x, float y) {
        this.points.add(new Vector2(x, y));
    }
    
    public float[] buildVertices() {
        float[] vertices = new float[this.points.size()*2];
        
        
        int verticesIndex = 0;
        for (Vector2 point : this.points) {
            vertices[verticesIndex] = point.x;
            vertices[verticesIndex+1] = point.y;
            verticesIndex += 2;
        }

        return vertices;
    }
}

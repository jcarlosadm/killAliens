package br.com.killaliens.util.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class CollisionPolygonWithCircle {
    
    public static boolean checkCollision(Polygon polygon, Circle circle){
        
        float[] vertices = polygon.getTransformedVertices();
        Vector2 center = new Vector2(circle.x, circle.y);
        float squareRadius = circle.radius * circle.radius;

        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[vertices.length - 2],
                        vertices[vertices.length - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            } else {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[i - 2], vertices[i - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            }
        }
        
        return polygon.contains(circle.x, circle.y);
    }
}

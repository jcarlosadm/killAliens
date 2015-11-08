package br.com.killaliens.util.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionChecker {

    /**
     * check if has collision between a polygon and a circle
     * @param polygon
     * @param circle
     * @return true if collision happens
     */
    public static boolean check(Polygon polygon, Circle circle) {

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
    
    /**
     * check if has collision between a rectangle and a point (with x and y coordinates)
     * @param rectangle
     * @param pointX
     * @param pointY
     * @return true if collision happens
     */
    public static boolean check(Rectangle rectangle, float pointX, float pointY){
        return rectangle.contains(pointX, pointY);
    }
    
    /**
     * check if has collision between two polygons
     * @param polygon1
     * @param polygon2
     * @return true if collision happens
     */
    public static boolean check(Polygon polygon1, Polygon polygon2) {
        return (Intersector.overlapConvexPolygons(polygon1, polygon2));
    }
    
    /**
     * check if has collision between a polygon and a point (with x and y coordinates)
     * @param polygon
     * @param pointX
     * @param pointY
     * @return true if collision happens
     */
    public static boolean check(Polygon polygon, float pointX, float pointY){
        return polygon.contains(pointX, pointY);
    }
    
    /**
     * check if has collision between a polygon and a rectangle
     * @param polygon
     * @param rectangle
     * @return true if collision happens
     */
    public static boolean check(Polygon polygon, Rectangle rectangle){
        Polygon rPoly = new Polygon(new float[] { 0, 0, rectangle.width, 0, rectangle.width,
                rectangle.height, 0, rectangle.height });
        rPoly.setPosition(rectangle.x, rectangle.y);
        
        if (Intersector.overlapConvexPolygons(rPoly, polygon)){
            return true;
        }
        
        return false;
    }

}

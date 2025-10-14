public class Circle {
    private final Vector center;
    private final double radius;
    
    public Circle(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    public Vector getCenter() {
        return center;
    }
    
    public boolean containsPoint(Vector point) {
        double distance = center.subtract(point).length();
        return distance <= radius;
    }
    
    @Override
    public String toString() {
        return "Circle[center=" + center + ", radius=" + radius + "]";
    }
}
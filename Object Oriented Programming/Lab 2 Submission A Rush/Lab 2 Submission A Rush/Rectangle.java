public class Rectangle {

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    
    public Rectangle(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public double getWidth() {
        return Math.abs(x2 - x1);
    }
    
    public double getHeight() {
        return Math.abs(y2 - y1);
    }
    
    public double getArea() {
        return getWidth() * getHeight();
    }
    
    public Vector getCenter() {
        double centerX = (x1 + x2) / 2.0;
        double centerY = (y1 + y2) / 2.0;
        return new Vector(centerX, centerY);
    }
    
    public boolean containsPoint(Vector point) {
        double px = point.getX();
        double py = point.getY();
        
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);
        
        return px >= minX && px <= maxX && py >= minY && py <= maxY;
    }
    
    @Override
    public String toString() {
        return "Rectangle[(" + x1 + "," + y1 + "), (" + x2 + "," + y2 + ")]";
    }
}
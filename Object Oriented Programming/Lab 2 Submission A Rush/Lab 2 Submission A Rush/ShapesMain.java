public class ShapesMain {
    public static void main(String[] args) {
        Vector v1 = new Vector(1, 5);
        Vector v2 = new Vector(4, 8);
        
        System.out.println("=== Testing Rectangle ===");
        Rectangle rect = new Rectangle(1, 5, 4, 8);
        System.out.println("Rectangle: " + rect);
        System.out.println("Width: " + rect.getWidth());
        System.out.println("Height: " + rect.getHeight());
        System.out.println("Area: " + rect.getArea());
        System.out.println("Center: " + rect.getCenter());
        
        Vector testPoint1 = new Vector(2.5, 6.5);
        Vector testPoint2 = new Vector(0, 0);
        System.out.println("Contains (2.5, 6.5): " + rect.containsPoint(testPoint1));
        System.out.println("Contains (0, 0): " + rect.containsPoint(testPoint2));
        
        System.out.println("\n=== Testing Circle ===");
        Vector circleCenter = new Vector(2.5, 6.5);
        Circle circle = new Circle(circleCenter, 2.0);
        System.out.println("Circle: " + circle);
        System.out.println("Area: " + circle.getArea());
        System.out.println("Center: " + circle.getCenter());
        
        Vector testPoint3 = new Vector(2.5, 6.5);
        Vector testPoint4 = new Vector(5, 9);
        System.out.println("Contains (2.5, 6.5): " + circle.containsPoint(testPoint3));
        System.out.println("Contains (5, 9): " + circle.containsPoint(testPoint4));
        
        System.out.println("\n=== Testing Vector Usage ===");
        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);
        System.out.println("v1 + v2: " + v1.add(v2));
        System.out.println("v2 - v1: " + v2.subtract(v1));
        System.out.println("Length of v1: " + v1.length());
    }
}
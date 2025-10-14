public class Vector {
    private double x;
    private double y;
    
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector scale(double factor) {
        double newX = this.x * factor;
        double newY = this.y * factor;
        return new Vector(newX, newY);
    }
    
    public Vector subtract(Vector other) {
        double newX = this.x - other.x;
        double newY = this.y - other.y;
        return new Vector(newX, newY);
    }
    
    public double length() {
        double rSquared = this.x * this.x + this.y * this.y;
        return Math.sqrt(rSquared);
    }
    
    public Vector add(Vector vector) {
        double newX = this.x + vector.x;
        double newY = this.y + vector.y;
        return new Vector(newX, newY);
    }
    
    public void printVector() {
        System.out.println("Vector x : " + this.x);
        System.out.println("Vector y : " + this.y);
        System.out.println("Vector length : " + this.length());
    }
    
    @Override
    public String toString() {
        return "Vector(" + this.x + ", " + this.y + ")";
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public static void main(String[] args) {
        Vector v1 = new Vector(3.0, 4.0);
        Vector v2 = new Vector(1.0, 2.0);
        
        System.out.println("Vector 1: " + v1);
        System.out.println("Vector 2: " + v2);
        System.out.println();
        
        Vector sum = v1.add(v2);
        System.out.println("v1 + v2 = " + sum);
        
        Vector difference = v1.subtract(v2);
        System.out.println("v1 - v2 = " + difference);
        
        Vector scaled = v1.scale(2.0);
        System.out.println("v1 * 2 = " + scaled);
        
        System.out.println("Length of v1: " + v1.length());
        System.out.println();
        
        System.out.println("Detailed info for v1:");
        v1.printVector();
    }
}
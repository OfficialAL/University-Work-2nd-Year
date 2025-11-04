/**
 * A class that records rainfall measurements and computes statistics.
 * This class tracks the count, total, and maximum of rainfall measurements.
 */
class RainfallStats {
    
    // Instance variables to store statistics
    private int count;
    private double total;
    private double max;
    
    /**
     * Constructor initializes all values to 0.
     */
    public RainfallStats() {
        count = 0;
        total = 0.0;
        max = 0.0;
    }
    
    /**
     * Adds a rainfall measurement to the statistics.
     * 
     * @param measurement the rainfall measurement in millimeters
     * @throws InvalidRainfallException if the measurement is negative
     */
    public void addMeasurement(double measurement) throws InvalidRainfallException {
        // Check if measurement is negative
        if (measurement < 0) {
            throw new InvalidRainfallException("Rainfall measurement cannot be negative: " + measurement);
        }
        
        // Update statistics
        count++;
        total += measurement;
        
        // Update maximum (for first measurement or if this is larger)
        if (count == 1 || measurement > max) {
            max = measurement;
        }
    }
    
    /**
     * Returns the number of measurements recorded.
     * 
     * @return the count of measurements as an int
     */
    public int getCount() {
        return count;
    }
    
    /**
     * Returns the mean of the rainfall measurements.
     * 
     * @return the mean rainfall as a double
     * @throws IllegalStateException if no measurements have been added
     */
    public double getMean() {
        if (count == 0) {
            throw new IllegalStateException("Cannot calculate mean: no measurements have been added");
        }
        return total / count;
    }
    
    /**
     * Returns the maximum rainfall measurement.
     * 
     * @return the maximum rainfall as a double
     * @throws IllegalStateException if no measurements have been added
     */
    public double getMax() {
        if (count == 0) {
            throw new IllegalStateException("Cannot get maximum: no measurements have been added");
        }
        return max;
    }
}

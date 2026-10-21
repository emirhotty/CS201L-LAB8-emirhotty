
public class Instrument {
    private String instrumentType;
    private String instrumentName;
    private String instrumentManufacturer;
    private int yearBuilt;
    private int cost;

    // Default constructor
    public Instrument() {}

    // Overloaded constructor
    public Instrument(String type, String name, String manufacturer, int year, int cost) {
        this.instrumentType = type;
        this.instrumentName = name;
        this.instrumentManufacturer = manufacturer;
        this.yearBuilt = year;
        this.cost = cost;
    }

    // Getters
    public String getType() { return instrumentType; }
    public String getName() { return instrumentName; }
    public String getManufacturer() { return instrumentManufacturer; }
    public int getYear() { return yearBuilt; }
    public int getCost() { return cost; }

    // Setters
    public void setType(String type) { this.instrumentType = type; }
    public void setName(String name) { this.instrumentName = name; }
    public void setManufacturer(String manufacturer) { this.instrumentManufacturer = manufacturer; }
    public void setYear(int year) { this.yearBuilt = year; }
    public void setCost(int cost) { this.cost = cost; }

    @Override
    public String toString() {
        String out = String.format("%-20s%-24s%-22s%8d%10d",
                instrumentType, instrumentName, instrumentManufacturer, yearBuilt, cost);
        return out;
    }
}

package sample.cafekiosk.unit.beverage;

public class Americano implements Beverage {

    private final String name;
    private final int price;

    public Americano() {
        this.name = "아메리카노";
        this.price = 4000;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

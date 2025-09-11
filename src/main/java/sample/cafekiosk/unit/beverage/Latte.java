package sample.cafekiosk.unit.beverage;

public class Latte implements Beverage {

    private final String name;
    private final int price;

    public Latte() {
        this.name = "라떼";
        this.price = 4500;
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

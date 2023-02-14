package main.java.org.rekrutacja;

public class SimpleBlock implements Block {
    private final String color;
    private final String material;

    public static SimpleBlock createWith(String color, String material) {
        return new SimpleBlock(color, material);
    }

    private SimpleBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }
}
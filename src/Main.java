import java.util.List;

public class Main {
    public static void main(String[] args) {
        var block1 = new SimpleBlock("red", "wood");
        var block2 = new SimpleBlock("blue", "wood");
        var block3 = new SimpleBlock("red", "metal");
        var block4 = new SimpleBlock("red", "metal");
        var block5 = new SimpleBlock("red", "metal");

        var compositeBlock1 = new SimpleCompositeBlock.Builder()
                .addBlock(block3)
                .addBlock(block4)
                .addBlock(block5)
                .setColor(block3.getColor())
                .setMaterial(block3.getMaterial())
                .build();

        var wall = new Wall(List.of(block1, block2, compositeBlock1));

        var count = wall.count();

        var foundBlock1 = wall.findBlockByColor(block1.getColor());
        var foundBlock2 = wall.findBlockByColor(block3.getColor());
        var foundBlock3 = wall.findBlockByColor("green");

        var foundBlocks1 = wall.findBlocksByMaterial(block2.getMaterial());
        var foundBlocks2 = wall.findBlocksByMaterial(block4.getMaterial());

        System.out.println("end\n");
    }
}
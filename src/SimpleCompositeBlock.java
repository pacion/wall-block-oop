import java.util.ArrayList;
import java.util.List;

public class SimpleCompositeBlock implements CompositeBlock {
    private final List<Block> blocks;
    private final String color;
    private final String material;

    private SimpleCompositeBlock(String color, String material, List<Block> blocks) {
        this.color = color;
        this.material = material;
        this.blocks = blocks;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    public static class Builder {
        private final List<Block> blocks = new ArrayList<>();
        private String color;
        private String material;

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder addBlock(Block block) {
            blocks.add(block);
            return this;
        }

        public SimpleCompositeBlock build() {
            return new SimpleCompositeBlock(color, material, blocks);
        }
    }
}
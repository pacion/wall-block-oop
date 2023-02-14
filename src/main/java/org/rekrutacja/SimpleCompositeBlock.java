package main.java.org.rekrutacja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCompositeBlock implements CompositeBlock {
    private final List<Block> blocks;

    public static class Builder {
        private final List<Block> blocks = new ArrayList<>();

        public Builder addBlock(Block block) {
            blocks.add(block);
            return this;
        }

        public SimpleCompositeBlock build() {
            return new SimpleCompositeBlock(blocks);
        }
    }

    private SimpleCompositeBlock(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    @Override
    public String getColor() {
        if (!blocks.isEmpty()) {
            return blocks.get(0).getColor();
        }

        return null;
    }

    @Override
    public String getMaterial() {
        if (!blocks.isEmpty()) {
            return blocks.get(0).getMaterial();
        }

        return null;
    }

    @Override
    public List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }
}
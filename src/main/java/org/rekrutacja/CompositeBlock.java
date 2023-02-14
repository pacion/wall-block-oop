package main.java.org.rekrutacja;

import java.util.List;

interface CompositeBlock extends Block {
    void addBlock(Block block);

    void removeBlock(Block block);

    List<Block> getBlocks();
}

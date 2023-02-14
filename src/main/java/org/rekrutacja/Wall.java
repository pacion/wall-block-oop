package main.java.org.rekrutacja;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Wall implements Structure {
    private List<Block> blocks;

    private Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    public static Wall createWith(List<Block> blocks) {
        return new Wall(blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        Predicate<Block> colorPredicate = block -> block.getColor().equals(color);

        return findBlockMatchingPredicate(colorPredicate);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        Predicate<Block> materialPredicate = block -> block.getMaterial().equals(material);

        return findBlocksMatchingPredicate(materialPredicate);
    }

    @Override
    public int count() {
        return (int) blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock ?
                        ((CompositeBlock) block).getBlocks().stream() :
                        Stream.of(block))
                .count();
    }

    private Optional<Block> findBlockMatchingPredicate(Predicate<Block> predicate) {
        return blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock ?
                        ((CompositeBlock) block).getBlocks().stream() :
                        Stream.of(block))
                .filter(predicate)
                .findFirst();
    }

    private List<Block> findBlocksMatchingPredicate(Predicate<Block> predicate) {
        return blocks.stream()
                .flatMap(block -> {
                    if (block instanceof CompositeBlock) {
                        return ((CompositeBlock) block).getBlocks().stream();
                    } else {
                        return Stream.of(block);
                    }
                })
                .filter(predicate)
                .toList();
    }
}
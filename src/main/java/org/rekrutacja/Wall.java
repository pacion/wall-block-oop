package main.java.org.rekrutacja;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

        return blocks.stream()
                .filter(colorPredicate)
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        Predicate<Block> materialPredicate = block -> block.getMaterial().equals(material);

        return blocks.stream()
                .flatMap(block -> {
                    if (block instanceof CompositeBlock) {
                        return ((CompositeBlock) block).getBlocks().stream();
                    } else {
                        return Stream.of(block);
                    }
                })
                .filter(materialPredicate)
                .collect(Collectors.toCollection(HashSet::new))
                .stream()
                .toList();
    }

    @Override
    public int count() {
        return blocks.stream()
                .mapToInt(block -> block instanceof CompositeBlock ?
                        ((CompositeBlock) block).getBlocks().size() :
                        1)
                .sum();
    }
}
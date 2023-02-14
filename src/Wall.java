import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .flatMap(block -> {
                    if (block instanceof CompositeBlock) {
                        return ((CompositeBlock) block).getBlocks().stream();
                    } else {
                        return Stream.of(block);
                    }
                })
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return (int) blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock ?
                        ((CompositeBlock) block).getBlocks().stream() :
                        Stream.of(block))
                .count();
    }
}
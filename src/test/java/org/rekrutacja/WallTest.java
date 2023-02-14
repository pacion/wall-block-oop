package test.java.org.rekrutacja;

import main.java.org.rekrutacja.SimpleBlock;
import main.java.org.rekrutacja.SimpleCompositeBlock;
import main.java.org.rekrutacja.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WallTest extends BaseTest {

    Wall wall;
    SimpleBlock redMetalBlock;
    SimpleBlock blueMetalBlock1;
    SimpleBlock blueMetalBlock2;
    SimpleBlock greenWoodenBlock;
    SimpleBlock blackBadBlock;
    SimpleCompositeBlock compositeBlock;

    private

    @BeforeEach
    void initContentForTests() {
        redMetalBlock = SimpleBlock.createWith(RED_COLOR, METAL_MATERIAL);
        blueMetalBlock1 = SimpleBlock.createWith(BLUE_COLOR, METAL_MATERIAL);
        blueMetalBlock2 = SimpleBlock.createWith(BLUE_COLOR, METAL_MATERIAL);
        greenWoodenBlock = SimpleBlock.createWith(GREEN_COLOR, WOOD_MATERIAL);
        blackBadBlock = SimpleBlock.createWith(BLACK_COLOR, BAD_MATERIAL);

        compositeBlock = new SimpleCompositeBlock.Builder()
                .addBlock(blueMetalBlock1)
                .addBlock(blueMetalBlock2)
                .build();

        wall = Wall.createWith(List.of(redMetalBlock, compositeBlock, greenWoodenBlock));
    }

    @Test
    void shouldFindBlockByRedColor() {
        var foundBlock = wall.findBlockByColor(RED_COLOR);

        assertTrue(foundBlock.isPresent());
        assertEquals(foundBlock.get(), redMetalBlock);
    }

    @Test
    void shouldFindBlockByGreenColor() {
        var foundBlock = wall.findBlockByColor(GREEN_COLOR);

        assertTrue(foundBlock.isPresent());
        assertEquals(foundBlock.get(), greenWoodenBlock);
    }

    @Test
    void shouldNotFindBlockByBadColor() {
        var foundBlock = wall.findBlockByColor(BAD_COLOR);

        assertTrue(foundBlock.isEmpty());
    }

    @Test
    void shouldFindBlockByBlueColorWhichIsInCompositeBlock() {
        var foundBlock = wall.findBlockByColor(BLUE_COLOR);

        assertTrue(foundBlock.isPresent());
        assertEquals(foundBlock.get(), compositeBlock);
        assertEquals(BLUE_COLOR, compositeBlock.getColor());
    }

    @Test
    void shouldGetCorrectCountOfBlocks() {
        var numberOfBlocks = wall.count();

        assertEquals(4, numberOfBlocks);
    }

    @Test
    void shouldFindWoodenBlock() {
        var foundBlocks = wall.findBlocksByMaterial(WOOD_MATERIAL);

        assertEquals(1, foundBlocks.size());
        assertEquals(greenWoodenBlock, foundBlocks.get(0));
    }

    @Test
    void shouldNotFindBadMaterialBlock() {
        var foundBlocks = wall.findBlocksByMaterial(BAD_MATERIAL);

        assertEquals(0, foundBlocks.size());
    }

    @Test
    void shouldAddBlockToCompositeBlock() {
        var newCompositeBlock = new SimpleCompositeBlock.Builder()
                .addBlock(blueMetalBlock1)
                .build();

        newCompositeBlock.addBlock(blueMetalBlock2);

        assertEquals(2, newCompositeBlock.getBlocks().size());
    }

    @Test
    void shouldRemoveBlockFromCompositeBlock() {
        var testCompositeBlock = new SimpleCompositeBlock.Builder()
                .addBlock(blueMetalBlock1)
                .build();

        testCompositeBlock.removeBlock(blueMetalBlock1);

        assertEquals(0, testCompositeBlock.getBlocks().size());
    }

    @Test
    void shouldGetUniqueBlocksNumber() {
        var testCompositeBlock = new SimpleCompositeBlock.Builder()
                .addBlock(blueMetalBlock1)
                .addBlock(blueMetalBlock1)
                .addBlock(blueMetalBlock1)
                .build();

        var testWall = Wall.createWith(List.of(testCompositeBlock, testCompositeBlock, blueMetalBlock2));

        var foundBlocks = testWall.findBlocksByMaterial(blueMetalBlock1.getMaterial());

        assertEquals(2, foundBlocks.size());
    }
}
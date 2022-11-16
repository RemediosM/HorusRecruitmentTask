package org.horus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    Block block1;
    Block block2;
    Block block3;
    CompositeBlock compositeBlock;
    Wall wall;

    @BeforeEach
    void setUp() {
        block1 = new BlockImpl("red","marble");
        block2 = new BlockImpl("blue", "concrete");
        block3 = new BlockImpl("green", "stone");
        compositeBlock = new CompositeBlockImpl(List.of(block1, block2));
        wall = new Wall(List.of(compositeBlock, block3));
    }

    @Test
    void findBlockByColor_color_exists_in_wall() {
        Optional<Block> testBlock = wall.findBlockByColor("red");

        assertEquals(block1, testBlock.isPresent() ? testBlock.get() : Optional.empty());
    }

    @Test
    void findBlockByColor_color_is_null(){
        Optional<Block> testBlock = wall.findBlockByColor(null);

        assertFalse(testBlock.isPresent());
    }

    @Test
    void findBlockByColor_color_not_exists_in_wall(){
        Optional<Block> testBlock = wall.findBlockByColor("yellow");
        
        assertFalse(testBlock.isPresent());
    }

    @Test
    void findBlockByColor_wall_is_empty(){
        Wall emptyWall = new Wall(null);

        Optional<Block> testBlock = emptyWall.findBlockByColor("green");

        assertFalse(testBlock.isPresent());
    }

    @Test
    void findBlocksByMaterial_material_exists_in_wall() {
        List<Block> testBlocks = wall.findBlocksByMaterial("stone");

        assertEquals(List.of(block3), testBlocks);
    }

    @Test
    void findBlocksByMaterial_material_is_null(){
        List<Block> testBlocks = wall.findBlocksByMaterial(null);

        assertEquals(new ArrayList<>(), testBlocks);
    }

    @Test
    void findBlocksByMaterial_material_not_exists_in_wall(){
        List<Block> testBlocks = wall.findBlocksByMaterial("a1");

        assertEquals(new ArrayList<>(), testBlocks);
    }

    @Test
    void findBlocksByMaterial_wall_is_empty(){
        Wall emptyWall = new Wall(null);

        List<Block> testBlocks = emptyWall.findBlocksByMaterial("concrete");

        assertEquals(new ArrayList<>(), testBlocks);
    }

    @Test
    void count_wall_is_not_empty() {
        int blocksCount = wall.count();

        assertEquals(3, blocksCount);
    }

    @Test
    void count_wall_is_empty() {
        Wall emptyWall = new Wall(null);

        int blocksCount = emptyWall.count();

        assertEquals(0, blocksCount);
    }
}
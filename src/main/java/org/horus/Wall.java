package org.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) {
            return Optional.empty();
        }
        return getFlatBlockStream()
                .filter(block -> color.equals(block.getColor()))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) {
            return new ArrayList<>();
        }

        return getFlatBlockStream()
                .filter(block -> material.equals(block.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return  getFlatBlockStream().collect(Collectors.toList()).size();
    }


    private Stream<Block> getFlatBlockStream() {
        if (this.blocks == null) {
            return Stream.empty();
        }

        return this.blocks.stream()
                .flatMap(this::extractBlocks);
    }

    private Stream<Block> extractBlocks(Block block) {
        if (block instanceof CompositeBlock) {
            return ((CompositeBlock) block).getBlocks().stream()
                    .flatMap(this::extractBlocks);
        }

        return Stream.of(block);
    }

}
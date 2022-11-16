package org.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = extractBlocks(blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) return Optional.empty();

        return blocks.stream()
                .filter(block -> color.equals(block.getColor()))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) return new ArrayList<>();

        return blocks.stream()
                .filter(block -> material.equals(block.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.size();
    }

    private List<Block> extractBlocks(List<Block> blocks) {
        if(blocks == null) return new ArrayList<>();

        return blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock ? ((CompositeBlock) block).getBlocks().stream() : Stream.of(block))
                .collect(Collectors.toList());
    }

}
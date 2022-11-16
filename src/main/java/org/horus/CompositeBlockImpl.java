package org.horus;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {
    private List<Block> blocks;

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}

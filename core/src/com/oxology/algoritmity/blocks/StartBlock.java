package com.oxology.algoritmity.blocks;

import com.oxology.algoritmity.utils.Block;
import com.oxology.algoritmity.utils.BlockType;
import com.oxology.algoritmity.utils.GameTexture;

public class StartBlock extends Block {

    public StartBlock(int x, int y) {
        super(x, y, GameTexture.START_BLOCK_TXT, BlockType.START);
    }
}

package com.oxology.algoritmity.blocks;

import com.oxology.algoritmity.GameManager;
import com.oxology.algoritmity.utils.GameTexture;

public class StartBlock extends Block {
    public StartBlock(int x, int y, GameManager gameManager) {
        super(x, y, GameTexture.BLOCK_START, BlockType.START, null, gameManager);
    }
}

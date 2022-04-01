package com.oxology.algoritmity.blocks;

import com.oxology.algoritmity.GameManager;
import com.oxology.algoritmity.utils.GameTexture;

public class StopBlock extends Block {
    public StopBlock(int x, int y, GameManager gameManager) {
        super(x, y, GameTexture.BLOCK_STOP, BlockType.STOP, GameTexture.BLOCK_STOP_SHADOW, gameManager);
    }
}

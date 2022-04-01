package com.oxology.algoritmity.blocks;

import com.oxology.algoritmity.utils.Block;
import com.oxology.algoritmity.utils.BlockType;
import com.oxology.algoritmity.utils.GameTexture;

public class AddBlock extends Block {
    float value;

    public AddBlock(int x, int y) {
        super(x, y, GameTexture.ADD_BLOCK_TXT, BlockType.MATH);
        this.value = 0;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

package com.vulture.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Sortable {
    public float getWorldX();
    public float getWorldY();
    public TextureRegion getSprite();
    public float getSizeX();
    public float getSizeY();
}

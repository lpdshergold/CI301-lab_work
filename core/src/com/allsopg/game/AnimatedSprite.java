package com.allsopg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;


public class AnimatedSprite extends Sprite {

    static final float FRAME_DURATION = 1.0f/30.0f;
    TextureAtlas atlas;
    Animation animation;
    Vector2 position;
    boolean moved = false;

    public AnimatedSprite(String atlasString, Vector2 position, Texture size)
    {
        super(size);
        this.position = position;
        this.setX(this.position.x);
        this.setY(this.position.y);
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));

        //Load animations
        Array<TextureAtlas.AtlasRegion> regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animation = new Animation(FRAME_DURATION, regions, Animation.PlayMode.LOOP);
    }

    public void setPlayMode(Animation.PlayMode pm)
    {
        animation.setPlayMode(pm);
    }

    public void update(float deltaTime)
    {
        //Update animation
        this.setRegion((TextureRegion)animation.getKeyFrame(deltaTime));

        //Update position
        if(moved)
        {
            this.setX(this.position.x);
            this.setY(this.position.y);
        }
    }

    public void updatePosData(Vector2 position)
    {
        this.position = position;
        moved = true;
    }


    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion>
    {

        @Override
        public int compare(TextureAtlas.AtlasRegion region_1, TextureAtlas.AtlasRegion region_2) {
            return region_1.name.compareTo(region_2.name);
        }
    }


}

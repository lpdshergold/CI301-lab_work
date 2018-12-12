package utility;


import com.allsopg.game.AnimatedSprite;
import com.allsopg.game.TestTweenAnimation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class BonusSprite extends AnimatedSprite {
    private TweenData tweenData;
    private TweenManager tweenManager;

    public BonusSprite(String atlasString, Texture t, Vector2 pos)
    {
        super(atlasString, pos, t);
        this.setPosition(pos.x, pos.y);
        initTweenData();

    }

    private void initTweenData()
    {
        tweenData = new TweenData();
        tweenData.setXy(new Vector2 (this.getX(), this.getY()));
        tweenData.setColor(this.getColor());
        tweenData.setScale(this.getScaleX());
        tweenManager = UniversalResource.getInstance().tweenManager; //tweenManager
    }

    private TweenData getTweenData()
    {
        return tweenData;
    }


    //put this in assignment code - can continue this to call for routines without worrying about
    //method names
    public void runRoutine(String name){
        if(name.equals("startroutine")){
            startRoutine();
        }
        else if(name.equals("closeroutine")){
            closeRoutine();;
        }

    }



    @Override
    public void update(float stateTime)
    {
        super.update(stateTime);
        this.setX(tweenData.getXy().x);
        this.setY(tweenData.getXy().y);
        this.setColor(tweenData.getColor());
        this.setScale(tweenData.getScale());
        this.setRotation(tweenData.getRotation());
    }

    public void startRoutine()
    {
        Timeline.createSequence()
                .push(Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION, 0f)
                        .target(90))
                .pushPause(100)
                .push(Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION, 10)
                        .target(360))
                .push(Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 75)
                        .target(getX(), 0))
                .setCallback(new TweenCallback() {
                                 @Override
                                 public void onEvent(int type, BaseTween<?> source) {
                                     setPlayMode(Animation.PlayMode.LOOP);
                                     TestTweenAnimation.text = "Go for it ball";
                                     closeRoutine();
                                 }
                             })
                .start(tweenManager);
    }

//    public void destroyRoutine()
//    {
//        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,250f)
//                .target(200,100).start(tweenManager).to(tweenData, TweenDataAccessor.TYPE_ROTATION,250f)
//                .target(180).start(tweenManager).to(tweenData, TweenDataAccessor.TYPE_SCALE,250f)
//                .target(.15f).start(tweenManager).to(tweenData,TweenDataAccessor.TYPE_COLOUR,500f)
//                .target(.15f,.15f,.15f,.0f).start(tweenManager);
//    }

    public void closeRoutine()
    {
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 100f)
                .delay(2000)
                .target(-250, 0).start(tweenManager)
                .setCallback(new TweenCallback() {
                                 @Override
                                 public void onEvent(int type, BaseTween<?> source) {
                                     setPlayMode(Animation.PlayMode.NORMAL);
                                     TestTweenAnimation.text = "Where you going?";
                                 }
                             })
                .start(tweenManager);
    }

}

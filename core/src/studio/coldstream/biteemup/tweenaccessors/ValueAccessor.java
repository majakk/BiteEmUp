package studio.coldstream.biteemup.tweenaccessors;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by majac on 2014-10-17.
 */
public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setValue(newValues[0]);
    }

}
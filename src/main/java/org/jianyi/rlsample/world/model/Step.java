package org.jianyi.rlsample.world.model;

import java.math.BigDecimal;

public class Step {

    private Action action;

    private BigDecimal value;

    public Step(Action action, BigDecimal value) {
        super();
        this.action = action;
        this.value = value;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String toString() {
        return "Step [action=" + this.action + ", value=" + this.value + "]";
    }

}

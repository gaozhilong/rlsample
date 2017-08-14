package org.jianyi.rlsample.world.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jianyi.rlsample.world.ActionType;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Value {

    private String id;

    private Map<ActionType, BigDecimal> actions;

    public Value() {
        super();
        this.actions = Maps.newEnumMap(ActionType.class);
    }

    public void addValue(ActionType action, BigDecimal value) {
        if (this.actions.containsKey(action)) {
            this.actions.put(action, this.actions.get(action).add(value)
                .setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            this.actions.put(action,
                value.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

    public BigDecimal getValue(ActionType action) {
        return this.actions.get(action);
    }

    public BigDecimal getMaxValue() {
        List<BigDecimal> values = new ArrayList<>(this.actions.values());
        Collections.reverse(values);
        return values.get(0);
    }

    public ActionType getMaxActionType() {
        ActionType actionType = null;
        List<BigDecimal> values = new ArrayList<>(this.actions.values());
        Collections.reverse(values);
        BigDecimal maxvalue = values.get(0);
        Set<ActionType> keys = this.actions.keySet();
        for (ActionType k : keys) {
            if (this.actions.get(k).equals(maxvalue)) {
                actionType = k;
            }
        }
        return actionType;
    }

    public List<ActionType> getSortActionTypes() {
        List<ActionType> actionTypes = Lists.newArrayList();
        List<BigDecimal> actionValues = new ArrayList<>(this.actions.values());
        Collections.reverse(actionValues);
        Set<ActionType> keys = this.actions.keySet();
        actionValues.forEach(it -> {
            for (ActionType k : keys) {
                if (this.actions.get(k).equals(it)) {
                    actionTypes.add(k);
                }
            }
        });
        return actionTypes;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<ActionType, BigDecimal> getActions() {
        return this.actions;
    }

    public void setActions(Map<ActionType, BigDecimal> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Value [actions=" + this.actions + "]";
    }

    /*
     * @Override
     * public String toString() {
     * StringBuilder actionstring = new StringBuilder("{");
     * this.actions.forEach((k, v) -> {
     * actionstring.append(k.toString());
     * actionstring.append("->");
     * actionstring.append(this.df.format(v));
     * actionstring.append(",");
     * });
     * actionstring.append("}");
     * return "Value [actions=" + actionstring + "]";
     * }
     */

}

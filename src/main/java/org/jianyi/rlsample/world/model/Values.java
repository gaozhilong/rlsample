package org.jianyi.rlsample.world.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jianyi.rlsample.world.ActionType;

import com.google.common.collect.Maps;

public class Values {

    private Map<String, Value> values;

    public Values() {
        super();
        this.values = Maps.newConcurrentMap();
    }

    public void addValue(String id, ActionType action, BigDecimal value) {
        if (this.values.containsKey(id)) {
            this.values.get(id).addValue(action, value);
        } else {
            Value actionValue = new Value();
            actionValue.setId(id);
            actionValue.addValue(action, value);
            this.values.put(id, actionValue);
        }
    }

    public Value getActionValue(String id) {
        Value value = null;
        if (this.values.containsKey(id)) {
            value = this.values.get(id);
        }
        return value;
    }

    public ActionType getMaxValueType(String id) {
        ActionType actionType = null;
        if (this.values.containsKey(id)) {
            Value value = this.values.get(id);
            actionType = value.getMaxActionType();
        }
        return actionType;
    }

    public ActionType pullValueType(String id) {
        ActionType actionType = null;
        if (this.values.containsKey(id)) {
            Value value = this.values.get(id);
            actionType = value.getMaxActionType();
        }
        return actionType;
    }

    public List<ActionType> getSortActionTypes(String id) {
        List<ActionType> actionTypes = null;
        if (this.values.containsKey(id)) {
            Value value = this.values.get(id);
            actionTypes = value.getSortActionTypes();
        }
        return actionTypes;
    }

    public BigDecimal getValue(String id, ActionType action) {
        BigDecimal value = new BigDecimal(0);
        if (this.values.containsKey(id)) {
            Value actionValue = this.values.get(id);
            if (actionValue.getActions().containsKey(action)) {
                value = actionValue.getActions().get(action);
            }
        }
        return value;
    }

    public Map<String, Value> getValues() {
        return this.values;
    }

    public void setValues(Map<String, Value> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Values [values=" + this.values + "]";
    }

}

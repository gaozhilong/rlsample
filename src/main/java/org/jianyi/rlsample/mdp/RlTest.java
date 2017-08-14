package org.jianyi.rlsample.mdp;

import java.math.BigDecimal;
import java.util.List;

import org.jianyi.rlsample.world.ActionType;
import org.jianyi.rlsample.world.AtionValuesUtil;
import org.jianyi.rlsample.world.ResultUtil;
import org.jianyi.rlsample.world.TMap;
import org.jianyi.rlsample.world.TMapStatusUtil;
import org.jianyi.rlsample.world.model.Action;
import org.jianyi.rlsample.world.model.Result;
import org.jianyi.rlsample.world.model.Status;
import org.jianyi.rlsample.world.model.Step;
import org.jianyi.rlsample.world.model.Values;

import com.google.common.collect.Lists;

public class RlTest {

    public static void main(String[] args) {
        TMap tmap = TMapStatusUtil.loadTMap();
        BigDecimal v = new BigDecimal(0);

        Values value = AtionValuesUtil.getModel(null);

        Status status = TMapStatusUtil.getInitStatus(tmap);

        ActionType actionType = value
            .getMaxValueType(status.getCurrentTile().getId());
        Action action = new Action(
            status.getCurrentTile().getId() + "-("
                + status.getCurrentTile().getX() + ","
                + status.getCurrentTile().getY() + ")-" + actionType,
            actionType);
        System.out.println("init Ststus:" + status);
        List<Step> steps = Lists.newArrayList();
        while (!status.isTerminal()) {
            System.out.println("Ststus:" + status);
            System.out.println("Action:" + action);
            status = TMapStatusUtil.doAction(action, status);
            List<ActionType> actionTypes = value
                .getSortActionTypes(status.getCurrentTile().getId());
            int i = 0;
            actionType = actionTypes.get(i);
            action = new Action(
                status.getCurrentTile().getId() + "-("
                    + status.getCurrentTile().getX() + ","
                    + status.getCurrentTile().getY() + ")-" + actionType,
                actionType);
            if (steps.size() == 1) {
                System.out.println("last step:" + steps.get(steps.size() - 1));
                while (action.getType().equals(ActionType.W)
                    || action.equals(steps.get(steps.size() - 1).getAction())) {
                    i++;
                    if (actionTypes.size() <= i) {
                        status.setTerminal(true);
                        break;
                    }
                    actionType = actionTypes.get(i);
                    action = new Action(status.getCurrentTile().getId() + "-("
                        + status.getCurrentTile().getX() + ","
                        + status.getCurrentTile().getY() + ")-" + actionType,
                        actionType);
                }
            } else if (steps.size() > 1) {
                System.out
                    .println("last steps(2):" + steps.get(steps.size() - 1)
                        + "," + steps.get(steps.size() - 2));
                while (action.getType().equals(ActionType.W)
                    || action.equals(steps.get(steps.size() - 1).getAction())
                    || action.equals(steps.get(steps.size() - 2).getAction())) {
                    i++;
                    if (actionTypes.size() <= i) {
                        status.setTerminal(true);
                        break;
                    }
                    actionType = actionTypes.get(i);
                    action = new Action(status.getCurrentTile().getId() + "-("
                        + status.getCurrentTile().getX() + ","
                        + status.getCurrentTile().getY() + ")-" + actionType,
                        actionType);
                }
            }
            Step step = new Step(action, v);
            steps.add(step);
        }
        Result result = new Result(status.getScore(), steps);

        steps.forEach(step -> {
            System.out.println(step);
        });
        System.out.println(result);
        ResultUtil.saveResult(result, null);
    }

}

package org.jianyi.rlsample.mdp;

import java.math.BigDecimal;
import java.util.List;

import org.jianyi.rlsample.world.AtionValuesUtil;
import org.jianyi.rlsample.world.TMap;
import org.jianyi.rlsample.world.TMapStatusUtil;
import org.jianyi.rlsample.world.model.Action;
import org.jianyi.rlsample.world.model.Result;
import org.jianyi.rlsample.world.model.Status;
import org.jianyi.rlsample.world.model.Step;
import org.jianyi.rlsample.world.model.Values;

import com.google.common.collect.Lists;

public class RlTrain {

    private static int TIMES = 100000;

    private static BigDecimal U = new BigDecimal(0.8);

    public static void main(String[] args) {
        TMap tmap = TMapStatusUtil.loadTMap();
        List<Result> values = Lists.newArrayList();
        Values actionValues = new Values();
        for (int i = 0; i < RlTrain.TIMES; i++) {
            BigDecimal gamma = new BigDecimal(1.0);
            BigDecimal v = new BigDecimal(0);
            Status status = TMapStatusUtil.getInitStatus(tmap);
            Action action = TMapStatusUtil
                .getRandomAction(status.getCurrentTile());
            System.out.println("init Ststus:" + status);
            List<Step> steps = Lists.newArrayList();
            while (!status.isTerminal()) {
                System.out.println("Ststus:" + status);
                System.out.println("Action:" + action);
                status = TMapStatusUtil.doAction(action, status);
                action = TMapStatusUtil
                    .getRandomAction(status.getCurrentTile());
                v = gamma.add(new BigDecimal(status.getScore()));
                actionValues.addValue(status.getCurrentTile().getId(),
                    action.getType(), v);
                gamma = gamma.multiply(RlTrain.U);
                Step step = new Step(action, v);
                steps.add(step);
            }
            Result result = new Result(status.getScore(), steps);
            values.add(result);
        }

        AtionValuesUtil.saveModel(actionValues, null);
        System.out.println(actionValues);
    }

}

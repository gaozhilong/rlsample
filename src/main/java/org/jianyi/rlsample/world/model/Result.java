package org.jianyi.rlsample.world.model;

import java.util.List;

public class Result {

    private int score;

    private List<Step> steps;

    public Result(int score, List<Step> steps) {
        super();
        this.score = score;
        this.steps = steps;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Result [score=" + this.score + ",step=" + this.steps.size()
            + ", steps=" + this.steps + "]";
    }

}

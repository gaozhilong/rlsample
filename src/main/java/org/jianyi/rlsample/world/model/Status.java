package org.jianyi.rlsample.world.model;

import org.jianyi.rlsample.world.TMap;
import org.jianyi.rlsample.world.Tile;

public class Status {

    private boolean terminal;

    private TMap tmap;

    private Tile currentTile;

    private int reward;

    private int score = 10;

    public Status(boolean terminal, TMap tmap, Tile currentTile, int reward,
            int score) {
        super();
        this.terminal = terminal;
        this.tmap = tmap;
        this.currentTile = currentTile;
        this.reward = reward;
        if (score > 0) {
            this.score = score;
        } else {
            this.score = tmap.getTotalScore();
        }
    }

    public boolean isTerminal() {
        return this.terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public TMap getTmap() {
        return this.tmap;
    }

    public void setTmap(TMap tmap) {
        this.tmap = tmap;
    }

    public Tile getCurrentTile() {
        return this.currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public int getReward() {
        return this.reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Status [terminal=" + this.terminal + ", currentTile="
            + this.currentTile + ", reward=" + this.reward + ", score="
            + this.score + "]";
    }

}

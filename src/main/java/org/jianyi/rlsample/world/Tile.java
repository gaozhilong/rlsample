package org.jianyi.rlsample.world;

import java.util.Map;

import com.google.common.collect.Maps;

public class Tile {

    private int x;

    private int y;

    private String id;

    private TileType type;

    private Map<ActionType, String> arounds;

    public Tile(String id, int x, int y, TileType type) {
        super();
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        this.arounds = Maps.newEnumMap(ActionType.class);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getType() {
        return this.type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Map<ActionType, String> getArounds() {
        return this.arounds;
    }

    public void setArounds(Map<ActionType, String> arounds) {
        this.arounds = arounds;
    }

    @Override
    public String toString() {
        return "Tile [x=" + this.x + ", y=" + this.y + ", id=" + this.id
            + ", type=" + this.type + ", arounds=" + this.arounds + "]";
    }

}

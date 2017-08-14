package org.jianyi.rlsample.world;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class TMap {

    private int x;

    private int y;

    private Tile[][] tiles;

    private Map<String, Tile> tilesMap;

    public TMap(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.tiles = new Tile[y][x];
        this.tilesMap = Maps.newConcurrentMap();
    }

    public void setTile(Tile tile, int xpos, int ypos) {
        this.tiles[ypos][xpos] = tile;
        this.tilesMap.put(tile.getId(), tile);
    }

    public Tile getTile(int xpos, int ypos) {
        return this.tiles[ypos][xpos];
    }

    public Tile getTile(String id) {
        return this.tilesMap.get(id);
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

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Map<String, Tile> getTilesMap() {
        return this.tilesMap;
    }

    public void setTilesMap(Map<String, Tile> tilesMap) {
        this.tilesMap = tilesMap;
    }

    public int getTotalScore() {
        int score = 0;
        Set<String> keys = this.tilesMap.keySet();
        for (String key : keys) {
            Tile tiler = this.tilesMap.get(key);
            if (tiler.getType().equals(TileType.P)) {
                Set<ActionType> as = tiler.getArounds().keySet();
                for (ActionType a : as) {
                    if (!this.tilesMap.get(tiler.getArounds().get(a)).getType()
                        .equals(TileType.W)) {
                        score = score + TileType.P.score();
                    }
                }
            }
        }
        return score;
    }

}

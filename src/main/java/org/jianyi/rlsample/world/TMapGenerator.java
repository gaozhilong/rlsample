package org.jianyi.rlsample.world;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TMapGenerator {

    public static final String MAP_DATA_PATH = "D:/";

    public static final String DATA_FILE_NAME = "tmap.json";

    private static final int SIZE = TileType.values().length;

    private static final Random RANDOM = new Random();

    public static TMap generateTMapDate(int x, int y) {
        Gson gson = new GsonBuilder().create();
        TMap tmap = TMapGenerator.generateTMap(x, y);
        while (tmap.getTotalScore() <= 0) {
            tmap = TMapGenerator.generateTMap(x, y);
        }
        String json = gson.toJson(tmap);
        String filepath = Resources.getResource("./").getPath()
            + TMapGenerator.DATA_FILE_NAME;
        System.out.println("学习样例数据路径：" + filepath);
        try {
            Files.write(json, new File(filepath), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmap;
    }

    private static Tile generateTile(String id, int x, int y) {
        Tile tile = new Tile(id, x, y, TileType.values()[TMapGenerator.RANDOM
            .nextInt(TMapGenerator.SIZE)]);
        return tile;
    }

    private static TMap generateTMap(int x, int y) {
        TMap tmap = new TMap(x, y);
        int idx = 0;
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                Tile tile = TMapGenerator.generateTile(String.valueOf(idx), i,
                    j);
                tile.setArounds(TMapGenerator.getArounds(tile, tmap));
                tmap.setTile(tile, i, j);
                idx++;
            }
        }
        return tmap;
    }

    private static Map<ActionType, String> getArounds(Tile tile, TMap tmap) {
        Map<ActionType, String> arounds = Maps.newEnumMap(ActionType.class);
        int a = tile.getX() - 1;
        int s = tile.getY() + 1;
        int d = tile.getX() + 1;
        int w = tile.getY() - 1;
        if (a >= 0 && tmap.getTile(a, tile.getY()) != null) {
            arounds.put(ActionType.A, tmap.getTile(a, tile.getY()).getId());
            tmap.getTile(a, tile.getY()).getArounds().put(ActionType.D,
                tile.getId());
        }
        if (d < tmap.getX() && tmap.getTile(d, tile.getY()) != null) {
            arounds.put(ActionType.D, tmap.getTile(d, tile.getY()).getId());
            tmap.getTile(d, tile.getY()).getArounds().put(ActionType.A,
                tile.getId());
        }
        if (w >= 0 && tmap.getTile(tile.getX(), w) != null) {
            arounds.put(ActionType.W, tmap.getTile(tile.getX(), w).getId());
            tmap.getTile(tile.getX(), w).getArounds().put(ActionType.S,
                tile.getId());
        }
        if (s < tmap.getX() && tmap.getTile(tile.getX(), s) != null) {
            arounds.put(ActionType.S, tmap.getTile(tile.getX(), s).getId());
            tmap.getTile(tile.getX(), s).getArounds().put(ActionType.W,
                tile.getId());
        }
        return arounds;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TMapGenerator.generateTMapDate(5, 5);
    }

}

package org.jianyi.rlsample.world;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.jianyi.rlsample.world.model.Action;
import org.jianyi.rlsample.world.model.Status;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TMapStatusUtil {

    private static final Random RANDOM = new Random();

    public static TMap loadTMap() {
        TMap tmap = null;
        Gson gson = new GsonBuilder().create();
        try {
            /*
             * URL url = Resources.getResource(
             * TMapGenerator.MAP_DATA_PATH + );
             */
            URL url = Resources
                .getResource("./" + TMapGenerator.DATA_FILE_NAME);
            String text = Resources.toString(url, Charsets.UTF_8);
            tmap = gson.fromJson(text, TMap.class);
        } catch (Exception e) {
            System.err.println("数据文件不存在");
            e.printStackTrace();
        }
        /*
         * if (tmap == null || tmap.getX() != x || tmap.getY() != y) {
         * tmap = TMapGenerator.generateTMapDate(x, y,
         * TMapStatusUtil.MAP_DATA_PATH);
         * }
         */
        return tmap;
    }

    public static void generateMap(int x, int y) {
        TMapGenerator.generateTMapDate(x, y);
    }

    public static Status getInitStatus(TMap tmap) {
        Tile startTile = TMapStatusUtil.getRandomTile(tmap);
        Status status = new Status(false, tmap, startTile, 0, -1);
        return status;
    }

    public static Tile getRandomTile(TMap tmap) {
        Tile[] tiles = tmap.getTilesMap().values()
            .toArray(new Tile[tmap.getTilesMap().size()]);
        return tiles[TMapStatusUtil.RANDOM.nextInt(tmap.getTilesMap().size())];
    }

    public static Action getRandomAction(Tile tile) {
        Map<ActionType, String> arounds = tile.getArounds();
        Action action = new Action();

        if (arounds != null && !arounds.isEmpty()) {
            List<ActionType> as = Lists.newArrayList(arounds.keySet());
            ActionType randomKey = as
                .get(TMapStatusUtil.RANDOM.nextInt(as.size()));
            action.setId(tile.getId() + "-(" + tile.getX() + "," + tile.getY()
                + ")-" + randomKey);
            action.setType(randomKey);
        }
        return action;
    }

    public static Status doAction(Action action, Status status) {
        Tile tile = status.getCurrentTile();
        TMap tmap = status.getTmap();
        Tile nextTile = tmap.getTile(tile.getArounds().get(action.getType()));
        boolean terminal = true;
        if (nextTile != null) {
            Set<String> keys = tmap.getTilesMap().keySet();
            //规则一
            for (String key : keys) {
                Tile tiler = tmap.getTilesMap().get(key);
                if (tiler.getType().equals(TileType.P)) {
                    Set<ActionType> as = tiler.getArounds().keySet();
                    for (ActionType a : as) {
                        if (!tmap.getTile(tiler.getArounds().get(a)).getType()
                            .equals(TileType.W)) {
                            terminal = false;
                        }
                    }
                }
            }
            if (!terminal) {
                int score = status.getScore() + nextTile.getType().score();
                if (score > 0) {
                    terminal = false;
                } else {
                    terminal = true;
                }
                if (!terminal) {
                    status.setScore(score);
                    if (nextTile.getType().equals(TileType.W)) {
                        nextTile = tile;
                    } else if (nextTile.getType().equals(TileType.P)) {
                        nextTile.setType(TileType.G);
                    }
                } else {
                    System.out.println("触发规则二");
                }
            } else {
                System.out.println("触发规则一");
            }
            status.setCurrentTile(nextTile);
        }
        status.setTerminal(terminal);
        return status;
    }

}

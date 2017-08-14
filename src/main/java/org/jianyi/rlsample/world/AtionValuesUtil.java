package org.jianyi.rlsample.world;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jianyi.rlsample.world.model.Values;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AtionValuesUtil {

    private static final String VALUE_FILE_NAME = "rlvalues.json";

    public static void saveModel(Values values, String filename) {
        String modelname = AtionValuesUtil.VALUE_FILE_NAME;
        if (filename != null && filename.endsWith(".json")) {
            modelname = filename;
        }
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(values);
        String filepath = Resources.getResource("./").getPath() + modelname;
        System.out.println("学习结果数据路径：" + filepath);
        try {
            Files.write(json, new File(filepath), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Values getModel(String filename) {
        Gson gson = new GsonBuilder().create();
        String modelname = AtionValuesUtil.VALUE_FILE_NAME;
        if (filename != null && filename.endsWith(".json")) {
            modelname = filename;
        }
        Values values = new Values();
        try {
            URL url = Resources.getResource("./" + modelname);
            String text = Resources.toString(url, Charsets.UTF_8);
            values = gson.fromJson(text, Values.class);
        } catch (Exception e) {
            System.err.println("数据文件不存在");
            e.printStackTrace();
        }
        return values;
    }

}

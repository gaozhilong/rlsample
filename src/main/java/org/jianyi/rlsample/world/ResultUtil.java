package org.jianyi.rlsample.world;

import java.io.File;
import java.io.IOException;

import org.jianyi.rlsample.world.model.Result;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResultUtil {

    private static final String RESULT_FILE_NAME = "result.json";

    public static void saveResult(Result result, String filename) {
        String resultName = ResultUtil.RESULT_FILE_NAME;
        if (filename != null && filename.endsWith(".json")) {
            resultName = filename;
        }
        resultName = System.currentTimeMillis() + resultName;
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(result);
        String filepath = Resources.getResource("./").getPath() + resultName;
        System.out.println("学习结果数据路径：" + filepath);
        try {
            Files.write(json, new File(filepath), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    public static String read(String filename, int line) {
        FileReader fileReader = null;
        BufferedReader reader = null;
        String content = null;
        try {
            fileReader = new FileReader(filename);
            reader = new BufferedReader(fileReader);
            for (int i = 0; i < line; i++) {
                content = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null && reader != null) {
                    fileReader.close();
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}

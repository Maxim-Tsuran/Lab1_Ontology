package ru.mpei.cimmaintainer;

import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator {
    public static void saveRawXml(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content); // Просто записываем как есть
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
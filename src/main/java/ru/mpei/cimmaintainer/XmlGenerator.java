package ru.mpei.cimmaintainer;

import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator {
    public static void saveRawXml(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Разбиваем на строки и фильтруем пустые
            String[] lines = content.split("\\r?\\n");
            for (String line : lines) {
                // Проверяем, что строка не пустая (после trim)
                if (!line.trim().isEmpty()) {
                    writer.write(line);
                    writer.write("\n"); // Добавляем перенос строки
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving XML: " + e.getMessage());
        }
    }
}
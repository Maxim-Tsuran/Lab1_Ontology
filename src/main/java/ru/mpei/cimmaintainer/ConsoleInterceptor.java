package ru.mpei.cimmaintainer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleInterceptor {
    private final ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;  // Теперь перехватываем только System.out

    public void start() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                outBuffer.write(b);  // Записываем в буфер
                originalOut.write(b);  // И выводим в оригинальный поток
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                outBuffer.write(b, off, len);
                originalOut.write(b, off, len);
            }
        }));
    }

    public String getOut() {
        return outBuffer.toString();
    }

    public void restore() {
        System.setOut(originalOut);
    }
}
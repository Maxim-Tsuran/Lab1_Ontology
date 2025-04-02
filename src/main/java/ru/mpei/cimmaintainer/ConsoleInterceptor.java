package ru.mpei.cimmaintainer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleInterceptor {
    private final ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void start() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                outBuffer.write(b);
                originalOut.write(b); // Для отображения в консоли IDE
            }
        }));

        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                errBuffer.write(b);
                originalErr.write(b); // Для отображения в консоли IDE
            }
        }));
    }

    public String getOut() {
        return outBuffer.toString();
    }

    public String getErr() {
        return errBuffer.toString();
    }

    public void restore() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
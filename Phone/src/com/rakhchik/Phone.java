package com.rakhchik;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone implements Closeable{

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Phone(String ip, int port) {
        try {
            this.socket = new Socket(ip,port);
            this.reader = initReader();
            this.writer = initWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Phone(ServerSocket server) {
        try {
            this.socket = server.accept();
            this.reader = initReader();
            this.writer = initWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader initReader() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private BufferedWriter initWriter() throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}

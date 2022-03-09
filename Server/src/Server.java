import com.rakhchik.Phone;

import java.io.*;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Server started!");

            while (true) {
                    Phone phone = new Phone(server);
                    new Thread(() -> {
                        String request = phone.readLine();
                        String response = request.equalsIgnoreCase("Ilya Rakhcheev") ? "He live in Moscow - Russia" : "I don't know";
                        try { Thread.sleep(5000); } catch (InterruptedException e) { throw new RuntimeException(e); }
                        phone.writeLine(response);

                        try { phone.close(); } catch (IOException e) { throw new RuntimeException(e); }

                        System.out.println("Request " + request);
                        System.out.println("Response " + response);
                    }).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

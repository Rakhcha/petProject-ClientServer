import com.rakhchik.Phone;

import java.io.*;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started!");

            while (true){
                try (Phone phone = new Phone(server)){

                    String request = phone.readLine();
                    String response = request.equalsIgnoreCase("Ilya Rakhcheev") ? "He live in Moscow - Russia" : "I don't know";
                    phone.writeLine(response);

                    System.out.println("Request " + request);
                    System.out.println("Response " + response);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

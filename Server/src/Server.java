import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        // Здесь необходимо указать порт, по которому будет работать сокет.
        // Проверка исключений, которые могут произойти в случае, например, если порт занят.
        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started!");

            // Здесь мы ожидаем подключения клиента и после создает сокет для него, проверяя исключения.

            // Создаем автоответчик.
            // socket.getInputStream(); - подключение потока для чтения; socket.getOutputStream() - получение потока для отправки
            // TODO Прочитать про это
            // Здесь мы создали простой поток, который умеет отправлять побайтовую информацию.
            // OutputStream stream = socket.getOutputStream();
            // Это не удобно, поэтому мы создаем BufferedWriter, который умеет больше.

            // Также создадим reader, который будет считывать информацию от клиента.
            while (true){
                try (Socket socket = server.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                    ){
                        // Сначала серверу необходимо что-либо прочитать, а после отправлять
                        String request = reader.readLine();
                        System.out.println("Client here! " + request);
                        String response = request.equalsIgnoreCase("Ilya Rakhcheev") ? "He live in Moscow - Russia" : "I don't know";
                        // 1. Отправка сообщения; 2. Переход на новую строку; 3. Отправка.
                        System.out.println(response);
                        writer.write("Response: " + response);
                        writer.newLine();
                        writer.flush();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

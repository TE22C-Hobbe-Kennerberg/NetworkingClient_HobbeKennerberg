import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket client = null;

        // Port number, same as server,
        int portNumber = 27051;
        for (int i = 0; i < 10; i++) {
            try{
                String msg = "";

                // Create client socket.
                client = new Socket(InetAddress.getLocalHost(), portNumber);
                System.out.println("Client socket is created.");

                // Create an output stream using the socket.
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create an input stream using the socket.
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                System.out.println("Type exit to close.");
                System.out.print("Send message: ");

                // Reads input.
                Scanner scanner = new Scanner(System.in);
                msg = scanner.nextLine().trim();
                pw.println(msg);

                // Read response from server.
                System.out.println("Server: " + br.readLine());
                pw.close();
                br.close();
                client.close();

                // Stops the operation
                if(msg.equalsIgnoreCase("exit")){
                    break;
                }
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
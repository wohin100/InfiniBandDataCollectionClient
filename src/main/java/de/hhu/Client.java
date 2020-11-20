package de.hhu;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

public class Client {
    private String hostname;
    private int port;
    private int timeOut;
    private Logger logger;
    private InetSocketAddress serverAddress;

    private PrintWriter printWriter;

    final String clientName = "\n\n[CLIENT ]: ";

    public Client(String hostname, int port, int timeOut, Logger logger) {
        this.hostname = hostname;
        this.port = port;
        this.timeOut = timeOut;
        this.logger = logger;

        this.serverAddress = new InetSocketAddress(hostname, port);

        connectToServer();
    }

    private void connectToServer() {
        logger.info(clientName + "Trying to connect to server");
        try {
            Socket socket = new Socket();
            socket.connect(serverAddress, timeOut);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");

            printWriter = new PrintWriter(outputStreamWriter);
            logger.info(clientName + "connected");
        } catch (IOException e) {
            logger.warning(clientName + "Failed to connect to server");
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    public void sendInfinibandDataToServer(String data) {
        printWriter.write(data);
        printWriter.flush();

        logger.info(clientName + "data successfully send");
    }
}

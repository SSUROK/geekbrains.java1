package geekbrains.java3.lesson6;

import ru.gb.chat.common.Common;
import ru.gb.javatwo.network.ServerSocketThread;
import ru.gb.javatwo.network.ServerSocketThreadListener;
import ru.gb.javatwo.network.SocketThread;
import ru.gb.javatwo.network.SocketThreadListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.*;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    private static final Logger logger = Logger.getLogger("MyFirstLogger");
    private ServerSocketThread server;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");
    private Vector<SocketThread> clients = new Vector<>();
    private ChatServerListener listener;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
        try {
            Handler h = new FileHandler("mylogsimple.log");
            h.setFormatter(new SimpleFormatter());
            logger.addHandler(h);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int port) {
        if (server != null && server.isAlive()) {
            logger.log(Level.SEVERE, "Server already stared");
            putLog("Server already stared");
        } else {
            server = new ServerSocketThread(this, "Chat server", port, 2000);
        }
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            logger.log(Level.SEVERE, "Server is not running");
            putLog("Server is not running");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        msg = DATE_FORMAT.format(System.currentTimeMillis()) +
                Thread.currentThread().getName() + ": " + msg;
        listener.onChatServerMessage(msg);
    }

    /**
     * Server Socket Thread Listener methods
     * */

    @Override
    public void onServerStart(ServerSocketThread thread) {
        logger.log(Level.SEVERE, "Server started");
        putLog("Server started");
        SqlClient.connect();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        logger.log(Level.SEVERE, "Server stopped");
        putLog("Server stopped");
        SqlClient.disconnect();
    }

    @Override
    public void onServerSocketCreated(ServerSocketThread thread, ServerSocket server) {
        logger.log(Level.SEVERE, "Listening to port");
        putLog("Listening to port");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
//        putLog("Ping? Pong!");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        // client connected
        String name = "Client " + socket.getInetAddress() + ":" + socket.getPort();
        new ClientThread(this, name, socket);
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable exception) {
        exception.printStackTrace();
    }

    /**
     * Socket Thread Listener methods
     * */

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        logger.log(Level.SEVERE, "Client thread started");
        putLog("Client thread started");
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        logger.log(Level.SEVERE, "Client thread stopped");
        putLog("Client thread stopped");
        clients.remove(thread);
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        logger.log(Level.SEVERE, "Client is ready to chat");
        putLog("Client is ready to chat");
        clients.add(thread);
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        ClientThread client = (ClientThread) thread;
        if (client.isAuthorized()) {
            handleAuthMessage(client, msg);
        } else {
            handleNonAuthMessage(client, msg);
        }
    }

    private void handleNonAuthMessage(ClientThread client, String msg) {
        String[] arr = msg.split(Common.DELIMITER);
        if (arr.length != 3 || !arr[0].equals(Common.AUTH_REQUEST)) {
            client.msgFormatError(msg);
            return;
        }
        String login = arr[1];
        String password = arr[2];
        String nickname = SqlClient.getNickname(login, password);
        if (nickname == null) {
            logger.log(Level.SEVERE, "Invalid login attempt: " + login);
            putLog("Invalid login attempt: " + login);
            client.authFail();
            return;
        }
        System.out.print(nickname);
        client.authAccept(nickname);
        sendToAllAuthorizedClients(Common.getTypeBroadcast("Server", nickname + " connected"));

    }

    private void handleAuthMessage(ClientThread client, String msg) {
        sendToAllAuthorizedClients(msg);
    }

    private void sendToAllAuthorizedClients(String msg) {
        for (int i = 0; i < clients.size(); i++) {
            ClientThread client = (ClientThread) clients.get(i);
            if (!client.isAuthorized()) continue;
            client.sendMessage(msg);
        }
    }

    @Override
    public void onSocketException(SocketThread thread, Exception exception) {
        exception.printStackTrace();
    }
}

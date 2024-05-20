package server;

import dao.FoodDao;
import dao.ItemDao;
import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4591)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket.getInetAddress().getHostAddress());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}

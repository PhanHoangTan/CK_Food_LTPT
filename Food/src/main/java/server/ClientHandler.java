package server;

import dao.FoodDao;
import dao.ItemDao;
import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;
import entity.Type;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;


class ClientHandler implements Runnable {
    private Socket socket;
    private FoodDao foodDao;
    private ItemDao itemDao;




    public  ClientHandler(Socket socket) throws RemoteException {
        this.socket = socket;
        foodDao = new FoodImpl();
        itemDao = new ItemImpl();
    }


    @Override
    public void run() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());


            int choice = 0;
            while (true) {
                try {
                    choice = dis.readInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Client requested list all items");
                            System.out.println("Câu a:");
                            String foodID = dis.readUTF();
                            String foodName = dis.readUTF();
                            double foodPrice = dis.readDouble();
                            String foodDescription = dis.readUTF();
                            boolean foodOnSpecial = dis.readBoolean();
                            Type foodType = Type.valueOf(dis.readUTF());
                            int foodPreparationTime = dis.readInt();
                            int foodServingTime = dis.readInt();

                            Food food = new Food(foodID, foodName,  foodDescription,foodPrice, foodOnSpecial, foodType, foodPreparationTime, foodServingTime);
                            boolean result = foodDao.addFood(food);
                            oos.writeBoolean(result);
                            oos.flush();


                            break;
                        case 2:
                            String supplierName = dis.readUTF();
                            List<Item> list = itemDao.listItems(supplierName);
                            oos.writeObject(list);

                            break;
                        case 3:
                            Map<Food, Double> foodAndCost = foodDao.listFoodAndCost();
                            oos.writeObject(foodAndCost);

                            break;
                        default:
                            break;
                    }
                } catch (EOFException e) {
                    System.out.println("Client disconnected");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
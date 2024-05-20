import entity.Food;
import entity.Item;
import entity.Type;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4591);
             Scanner scanner = new Scanner(System.in);)
        {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server");
            int choice = 0;
            while (true){
                System.out.println("1. Theêm thức ăn mới: ");
                System.out.println("2. Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng");
                System.out.println("3. Giá gốc của từng món ăn sau khi chế biết thành phẩm.");
                System.out.println("4. Exit");
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                dos.writeInt(choice);
                dos.flush();
                switch (choice){

                    case 1:
                        scanner.nextLine();
                        System.out.println("Nhập mã thức ăn: ");
                        String foodID = scanner.nextLine();
                        dos.writeUTF(foodID);

                        System.out.println("Nhập tên thức ăn: ");
                        String foodName = scanner.nextLine();
                        dos.writeUTF(foodName);

                        System.out.println("Nhập giá thức ăn: ");
                        double foodPrice = scanner.nextDouble();
                        dos.writeDouble(foodPrice);
                        scanner.nextLine();

                        System.out.println("Nhập mô tả thức ăn: ");
                        String foodDescription = scanner.nextLine();
                        dos.writeUTF(foodDescription);

                        System.out.println("Nhập trạng thái đặc biệt: ");
                        boolean foodOnSpecial = scanner.nextBoolean();
                        dos.writeBoolean(foodOnSpecial);

                        System.out.println("Nhập loại thức ăn: ");
                        Type foodType = Type.valueOf(scanner.next());
                        dos.writeUTF(foodType.toString());

                        System.out.println("Nhập thời gian chuẩn bị: ");
                        int foodPreparationTime = scanner.nextInt();
                        dos.writeInt(foodPreparationTime);

                        System.out.println("Nhập thời gian phục vụ: ");
                        int foodServingTime = scanner.nextInt();
                        dos.writeInt(foodServingTime);

                        dos.flush();

                        boolean result = ois.readBoolean();
                        if (result)
                        {
                            System.out.println("Thêm thành công");
                        }
                        else
                        {
                            System.out.println("Thêm thất bại");
                        }




                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.println("Nhaap tên nhà cung cấp: ");
                        String supplierName = scanner.nextLine();
                        dos.writeUTF(supplierName);
                        dos.flush();

                        List<Item> list = (List<Item>) ois.readObject();
                        for (Item item: list)
                        {
                            System.out.println(item);
                        }

                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.println("Giá gốc của từng món ăn sau khi chế biết thành phẩm.");

                        Map<Item, Double> itemAndCost = (Map<Item, Double>) ois.readObject();
                        for (Map.Entry<Item, Double> entry: itemAndCost.entrySet())
                        {
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        }
                        break;







                    case 7:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

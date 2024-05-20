package dao.impl;

import dao.FoodDao;
import entity.Food;
import entity.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodImpl extends UnicastRemoteObject implements FoodDao, Serializable {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public FoodImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    //Them mon an vao danh sach
    //addFood(Food): boolean
    @Override
    public boolean addFood(Food food) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(food);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
//    Tính giá gốc của từng món ăn sau khi chế biết thành phẩm. Kết quả sắp xếp giảm
//    dần theo đơn giá gốc.
//    Trong đó: Giá gốc món ăn = (số lượng nguyên liệu * đơn giá nguyên liệu) + (thời gian chuẩn
//    bị + thời gian phục vụ) * 0.2$
//+ public listFoodAndCost(): Map<Food, Double>


    @Override
    public Map<Food, Double> listFoodAndCost() throws RemoteException {
        Map<Food, Double> foodCosts = new LinkedHashMap<>();
        String query = "SELECT f.id, f.name, f.price, f.description, f.onSpecial, f.type, f.preparationTime, f.servingTime, SUM(i.quantity * i.price) + (f.preparationTime + f.servingTime) * 0.2 " +
                "FROM Food f INNER JOIN f.ingredients i " +
                "INNER JOIN i.items it " +
                "GROUP BY f.id, f.name, f.price, f.description, f.onSpecial, f.type, f.preparationTime, f.servingTime";
        List<Object[]> resultList = em.createQuery(query).getResultList();
        for (Object[] obj : resultList) {
            Food food = new Food();
            food.setId((String) obj[0]);
            food.setName((String) obj[1]);
            food.setPrice((Double) obj[2]);
            food.setDescription((String) obj[3]);
            food.setOnSpecial((Boolean) obj[4]);
            food.setType((entity.Type) obj[5]);
            food.setPreparationTime((Integer) obj[6]);
            food.setServingTime((Integer) obj[7]);
            foodCosts.put(food, (Double) obj[8]);
        }
        return foodCosts;
    }




}
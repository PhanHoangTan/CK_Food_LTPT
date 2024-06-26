package dao.impl;

import dao.ItemDao;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ItemImpl extends UnicastRemoteObject implements ItemDao, Serializable {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public ItemImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }
//    Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng mà có sử dụng nguyên
//    liệu được nhập từ nhà cung cấp nào đó khi biết tên nhà cung cấp (tìm tương đối, không phân biệt
//            chữ thường hoa).

    public List<Item> listItems(String supplierName) throws RemoteException {
        return em.createQuery("SELECT i FROM Item i INNER JOIN i.ingredients ig WHERE LOWER(ig.supplierName) LIKE LOWER(:supplierName) and i.onSpecial = true")
                .setParameter("supplierName", "%" + supplierName + "%")
                .getResultList();
    }
}
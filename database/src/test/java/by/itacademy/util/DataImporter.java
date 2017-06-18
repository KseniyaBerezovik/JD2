package by.itacademy.util;

import by.itacademy.dao.OrderDao;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderDetail;
import by.itacademy.entity.otherEntity.Address;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class DataImporter {
    private static DataImporter INSTANCE;

    private DataImporter() {}

    public static DataImporter getInstance() {
        if(INSTANCE == null) {
            synchronized (DataImporter.class){
                if(INSTANCE == null) {
                    INSTANCE = new DataImporter();
                }
            }
        }
        return INSTANCE;
    }

    public void importData(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Category mobilePhone = new Category();
        mobilePhone.setName("Мобильные телефоны");
        session.save(mobilePhone);

        Category tv = new Category();
        tv.setName("Телевизоры");
        session.save(tv);

        Detail yearOfIssue = saveDetail(session, "Год выпуска", mobilePhone, tv);
        Detail country = saveDetail(session, "Страна-производитель", mobilePhone, tv);
        Detail battery = saveDetail(session, "Аккумулятор", mobilePhone);
        Detail os = saveDetail(session, "Операционная система", mobilePhone);

        Product xiaomi = saveProduct(session,
                "Xiaomi Redmi 3",
                "xiaomi description",
                360d,
                mobilePhone);

        Product samsungTv = saveProduct(session,
                "TV Samsung",
                "samsung description",
                520d,
                tv);

        saveCharacteristic(session, xiaomi, yearOfIssue, "2017");
        saveCharacteristic(session, xiaomi, country, "Китай");
        saveCharacteristic(session, xiaomi, battery, "2000");
        saveCharacteristic(session, xiaomi, os, "Android");

        saveCharacteristic(session, samsungTv, yearOfIssue, "2016");
        saveCharacteristic(session, samsungTv, country, "Австрия");

        Client client = saveClient(session);

        saveReview(session, client, xiaomi);
        saveOrder(session, client);

        transaction.commit();
        session.close();
    }

    private void saveCharacteristic(Session session,  Product product, Detail detail, String value) {
        Characteristic characteristic = new Characteristic();
        characteristic.setProduct(product);
        characteristic.setDetail(detail);
        characteristic.setValue(value);
        session.save(characteristic);
    }

    private Product saveProduct(Session session, String name, String description, Double price, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        session.save(product);
        return product;
    }

    private Detail saveDetail(Session session, String name, Category ... categories) {
        Detail detail = new Detail();
        detail.setName(name);
        for(Category category : categories) {
            category.getDetails().add(detail);
        }
        session.save(detail);
        return detail;
    }

    private Review saveReview(Session session, Client owner, Product product) {
        Review review = new Review();
        review.setOwner(owner);
        review.setProduct(product);
        review.setDateOfCreation(LocalDateTime.now());
        review.setContent("Very good!");
        session.save(review);
        return review;
    }

    private Client saveClient(Session session) {
        Client client = new Client();
        client.setName("Max");
        client.setSurname("Ivanov");
        client.setLogin("mivan");
        client.setPassword("1111");

        Address address = new Address();
        address.setCity("Minsk");

        client.setAddress(address);
        session.save(client);
        return client;
    }

    private Order saveOrder(Session session, Client client) {
        Order order = new Order();
        order.setOwner(client);

        OrderDetail detail = new OrderDetail();
        detail.setDateOfReceipt(LocalDateTime.of(2017, 5, 5, 0, 0));
        session.save(detail);
        order.setDetail(detail);
        session.save(order);
        return order;
    }
}
package by.itacademy.util;

import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderDetail;
import by.itacademy.entity.otherEntity.Address;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataImporterImpl implements DataImporter {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void importData() {
        Session session = sessionFactory.getCurrentSession();

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

        User client = saveUser(session);

        saveReview(session, client, xiaomi);
        saveOrder(session, client);
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

    private Review saveReview(Session session, User owner, Product product) {
        Review review = new Review();
        review.setOwner(owner);
        review.setProduct(product);
        review.setDateOfCreation(LocalDateTime.now());
        review.setContent("Very good!");
        session.save(review);
        return review;
    }

    private User saveUser(Session session) {
        User user = new User();
        user.setName("Max");
        user.setSurname("Ivanov");
        user.setLogin("mivan");
        user.setPassword("1111");

        Address address = new Address();
        address.setCity("Minsk");

        user.setAddress(address);
        session.save(user);
        return user;
    }

    private Order saveOrder(Session session, User user) {
        Order order = new Order();
        order.setOwner(user);

        OrderDetail detail = new OrderDetail();
        detail.setDateOfReceipt(LocalDateTime.of(2017, 5, 5, 0, 0));
        session.save(detail);
        order.setDetail(detail);
        session.save(order);
        return order;
    }
}
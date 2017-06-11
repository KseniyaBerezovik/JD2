package by.itacademy.dao.util;

import by.itacademy.entity.*;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import by.itacademy.entity.orderEntity.OrderDetail;
import by.itacademy.entity.orderEntity.OrderStatus;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.Address;
import by.itacademy.entity.userEntity.Admin;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.entity.userEntity.Employee;

import java.time.LocalDateTime;

public class EntityBuilder {
    public static Address createAddress() {
        Address address = new Address();
        address.setCity("Minsk");
        address.setStreet("Shugaeva");
        address.setHouse("6a");
        address.setFlat(13);
        return address;
    }

    public static Client createClient() {
        Client client = new Client();
        client.setName("Anna");
        client.setSurname("Morozova");
        client.setLogin("amor");
        client.setPassword("qwerty");
        client.setAddress(createAddress());
        return client;
    }

    public static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setName("Ivan");
        employee.setSurname("Petrov");
        employee.setLogin("ipert");
        employee.setPassword("1234");
        employee.setPayment(640.5);
        return employee;
    }

    public static Order createOrder() {
        Order order = new Order();
        order.setOwner(createClient());
        order.setEmployee(createEmployee());
        order.setStatus(OrderStatus.FORMED);
        return order;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setName("Xiaomi Redmi 3");
        product.setDescription("description");
        product.setPrice(340d);
        return product;
    }

    public static OrderContent createOrderContent() {
        OrderContent orderContent = new OrderContent();
        orderContent.setProduct(createProduct());
        orderContent.setAmount(2);
        orderContent.setOrder(createOrder());
        return orderContent;
    }

    public static OrderDetail createOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDateOfReceipt(LocalDateTime.now());
        orderDetail.setOrder(createOrder());
        return orderDetail;
    }

    public static Admin createAdmin() {
        Admin admin = new Admin();
        admin.setName("Max");
        admin.setSurname("Petrov");
        admin.setLogin("mpetr");
        admin.setEmail("max.petrov@gmail.com");
        admin.setPhone("+375298886611");
        return admin;
    }

    public static Task createTask() {
        Task task = new Task();
        task.setName("Delete old orders");
        task.setContent("Delete orders before 01.01.2017");
        task.setStatus(TaskStatus.NOT_DONE);
        task.setAdmin(createAdmin());
        return task;
    }

    public static Review createReview() {
        Review review = new Review();
        review.setOwner(createClient());
        review.setDateOfCreation(LocalDateTime.now());
        review.setContent("Very good!!!");
        review.setProduct(createProduct());
        return review;
    }

    public static Promotion createPromotion() {
        Promotion promotion = new Promotion();
        promotion.setDateOfCreation(LocalDateTime.now());
        promotion.setContent("-30%");
        promotion.setDescription("all July -30%");
        promotion.getClients().add(createClient());
        return promotion;
    }

    public static Message createMessage() {
        Message message = new Message();
        message.setRecipient(createEmployee());
        message.setSender(createClient());
        message.setContent("Hello!");
        message.setDate(LocalDateTime.now());
        return message;
    }

    public static Characteristic createCharacteristic() {
        Characteristic characteristic = new Characteristic();
        characteristic.setProduct(createProduct());
        characteristic.setCriterion("year of issue");
        characteristic.setValue("2017");
        return characteristic;
    }

}

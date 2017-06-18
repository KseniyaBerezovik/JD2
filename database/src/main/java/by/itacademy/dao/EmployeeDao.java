package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.Employee;

public class EmployeeDao extends BaseDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    private static EmployeeDao INSTANCE;

    public static EmployeeDao getInstance() {
        if(INSTANCE == null) {
            synchronized (ClientDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new EmployeeDao();
                }
            }
        }
        return INSTANCE;
    }
}
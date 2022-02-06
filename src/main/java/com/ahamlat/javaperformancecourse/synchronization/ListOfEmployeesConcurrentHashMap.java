package com.ahamlat.javaperformancecourse.synchronization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListOfEmployeesConcurrentHashMap implements ListOfEmployees {

    Map<Integer, Employee> employees = new ConcurrentHashMap<>();

    public void addEmployee(Integer id, Employee newEmployee) {
        employees.put(id, newEmployee);

    }

    public Employee getEmployee(Integer id) {
        return employees.get(id);
    }


}

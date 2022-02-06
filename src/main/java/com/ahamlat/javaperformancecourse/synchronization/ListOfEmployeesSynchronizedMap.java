package com.ahamlat.javaperformancecourse.synchronization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListOfEmployeesSynchronizedMap implements ListOfEmployees{

    Map<Integer, Employee> employees = Collections.synchronizedMap(new HashMap<>());

    public void addEmployee(Integer id, Employee newEmployee) {
        employees.put(id, newEmployee);

    }

    public Employee getEmployee(Integer id) {
            return employees.get(id);
    }


}

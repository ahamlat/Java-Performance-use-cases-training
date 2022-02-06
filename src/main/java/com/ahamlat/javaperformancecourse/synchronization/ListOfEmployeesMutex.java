package com.ahamlat.javaperformancecourse.synchronization;

import java.util.HashMap;
import java.util.Map;

public class ListOfEmployeesMutex implements ListOfEmployees{

    Map<Integer, Employee> employees = new HashMap<>();
    final Object mutex;

    public ListOfEmployeesMutex() {
        this.mutex = this;
    }

    public void addEmployee(Integer id, Employee newEmployee) {
        synchronized (mutex) {
            employees.put(id, newEmployee);
        }
    }

    public Employee getEmployee(Integer id) {
        synchronized (mutex) {
            return employees.get(id);
        }
    }


}

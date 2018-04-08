package com.almundo.callcenter.employee;

import com.almundo.callcenter.employee.Role;

/**
 * The properties of this class are final and there is no setters because is not
 * intended to change.
 */
public class Employee
{
    private final int id;
    private final String name;
    private final Role role;

    public Employee(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }
}

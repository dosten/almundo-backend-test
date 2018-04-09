package com.almundo.callcenter.employee;

import java.util.Comparator;

import com.almundo.callcenter.employee.Employee;

/**
 * This comparator compares employees by priority, ordering from high to low priority.
 */
public class RoleComparator implements Comparator<Employee>
{
    @Override
    public int compare(Employee a, Employee b) {
        // If priority(a) > priority(b) then priority(b) - priority(a) < 0
        // If priority(a) < priority(b) then priority(b) - priority(a) > 0
        // If priority(a) = priority(b) then priority(b) - priority(a) = 0
        return b.getRole().getPriority() - a.getRole().getPriority();
    }
}

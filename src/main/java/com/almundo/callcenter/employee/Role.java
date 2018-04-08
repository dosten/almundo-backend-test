package com.almundo.callcenter.employee;

/**
 * Since this is a call center, the employees with the most priority must be
 * the operators because they are intended to answer the calls.
 */
public enum Role {
    OPERATOR(3),
    SUPERVISOR(2),
    DIRECTOR(1);

    private final int priority;

    Role(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }
}

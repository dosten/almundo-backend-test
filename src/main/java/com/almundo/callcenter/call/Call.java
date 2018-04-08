package com.almundo.callcenter.call;

import java.util.Optional;

import com.almundo.callcenter.call.Status;
import com.almundo.callcenter.employee.Employee;

public class Call
{
    private Optional<Employee> attendant = Optional.empty();
    private Status status = Status.STANDBY;
    private int duration = 0;

    public Optional<Employee> getAttendant() {
        return this.attendant;
    }

    public void setAttendant(Optional<Employee> attendant) {
        this.attendant = attendant;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

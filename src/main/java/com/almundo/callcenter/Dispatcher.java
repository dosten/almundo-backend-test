package com.almundo.callcenter;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Optional;

import com.almundo.callcenter.call.Call;
import com.almundo.callcenter.call.Status;
import com.almundo.callcenter.employee.Employee;
import com.almundo.callcenter.employee.RoleComparator;

/**
 * For more information about how this class works see README.md
 */
public class Dispatcher
{
    private ExecutorService executorService;
    private PriorityBlockingQueue<Employee> availableAttendants;

    public Dispatcher(int maxConcurrentCalls) {
        this.executorService = Executors.newFixedThreadPool(maxConcurrentCalls);
        this.availableAttendants = new PriorityBlockingQueue<Employee>(10, new RoleComparator());
    }

    public void clockinAttendant(Employee employee) {
        this.availableAttendants.put(employee);
    }

    public void clockoutAttendant(Employee employee) {
        this.availableAttendants.remove(employee);
    }

    public void dispatchCall(Call call) {
        this.executorService.submit(() -> this.assignCall(call));
    }

    private void assignCall(Call call) {
        try {
            Employee attendant = this.availableAttendants.take();
            call.setAttendant(Optional.of(attendant));
            call.setStatus(Status.CONNECTED);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void endCall(Call call) {
        Optional<Employee> employee = call.getAttendant();

        // a call can be ended before a employee is assigned. e.g: the client cuts
        if (employee.isPresent()) {
            this.availableAttendants.put(employee.get());
        }

        call.setStatus(Status.ENDED);
    }
}

package com.almundo.callcenter;

import java.lang.InterruptedException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.almundo.callcenter.call.Call;
import com.almundo.callcenter.call.Status;
import com.almundo.callcenter.Dispatcher;
import com.almundo.callcenter.employee.Employee;
import com.almundo.callcenter.employee.Role;

public class DispatcherTest extends TestCase
{
    public DispatcherTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(DispatcherTest.class);
    }

    public void testDispatcherWithTenEmployeesAndTenCalls() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(10); // numCalls

        Employee operator1 = new Employee(1, "Operator 1", Role.OPERATOR);
        Employee operator2 = new Employee(2, "Operator 2", Role.OPERATOR);
        Employee operator3 = new Employee(3, "Operator 3", Role.OPERATOR);
        Employee operator4 = new Employee(4, "Operator 4", Role.OPERATOR);
        Employee operator5 = new Employee(5, "Operator 5", Role.OPERATOR);
        Employee operator6 = new Employee(6, "Operator 6", Role.OPERATOR);
        Employee operator7 = new Employee(7, "Operator 7", Role.OPERATOR);
        Employee supervisor1 = new Employee(8, "Supervisor 1", Role.SUPERVISOR);
        Employee supervisor2 = new Employee(9, "Supervisor 2", Role.SUPERVISOR);
        Employee director1 = new Employee(10, "Director 1", Role.DIRECTOR);

        Dispatcher dispatcher = new Dispatcher(10);

        dispatcher.clockinAttendant(operator1);
        dispatcher.clockinAttendant(operator2);
        dispatcher.clockinAttendant(operator3);
        dispatcher.clockinAttendant(operator4);
        dispatcher.clockinAttendant(operator5);
        dispatcher.clockinAttendant(operator6);
        dispatcher.clockinAttendant(operator7);
        dispatcher.clockinAttendant(supervisor1);
        dispatcher.clockinAttendant(supervisor2);
        dispatcher.clockinAttendant(director1);

        Call call1 = new Call();
        Call call2 = new Call();
        Call call3 = new Call();
        Call call4 = new Call();
        Call call5 = new Call();
        Call call6 = new Call();
        Call call7 = new Call();
        Call call8 = new Call();
        Call call9 = new Call();
        Call call10 = new Call();

        service.submit(() -> this.emulateCall(dispatcher, call1));
        service.submit(() -> this.emulateCall(dispatcher, call2));
        service.submit(() -> this.emulateCall(dispatcher, call3));
        service.submit(() -> this.emulateCall(dispatcher, call4));
        service.submit(() -> this.emulateCall(dispatcher, call5));
        service.submit(() -> this.emulateCall(dispatcher, call6));
        service.submit(() -> this.emulateCall(dispatcher, call7));
        service.submit(() -> this.emulateCall(dispatcher, call8));
        service.submit(() -> this.emulateCall(dispatcher, call9));
        service.submit(() -> this.emulateCall(dispatcher, call10));

        service.shutdown();
        service.awaitTermination(15, TimeUnit.SECONDS);

        assertEquals(Role.OPERATOR, call1.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call2.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call3.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call4.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call5.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call6.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call7.getAttendant().get().getRole());
        assertEquals(Role.SUPERVISOR, call8.getAttendant().get().getRole());
        assertEquals(Role.SUPERVISOR, call9.getAttendant().get().getRole());
        assertEquals(Role.DIRECTOR, call10.getAttendant().get().getRole());
    }

    public void testDispatcherWithTenEmployeesAndElevenCalls() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(11); // numCalls

        Employee operator1 = new Employee(1, "Operator 1", Role.OPERATOR);
        Employee operator2 = new Employee(2, "Operator 2", Role.OPERATOR);
        Employee operator3 = new Employee(3, "Operator 3", Role.OPERATOR);
        Employee operator4 = new Employee(4, "Operator 4", Role.OPERATOR);
        Employee operator5 = new Employee(5, "Operator 5", Role.OPERATOR);
        Employee operator6 = new Employee(6, "Operator 6", Role.OPERATOR);
        Employee operator7 = new Employee(7, "Operator 7", Role.OPERATOR);
        Employee supervisor1 = new Employee(8, "Supervisor 1", Role.SUPERVISOR);
        Employee supervisor2 = new Employee(9, "Supervisor 2", Role.SUPERVISOR);
        Employee director1 = new Employee(10, "Director 1", Role.DIRECTOR);

        Dispatcher dispatcher = new Dispatcher(10);

        dispatcher.clockinAttendant(operator1);
        dispatcher.clockinAttendant(operator2);
        dispatcher.clockinAttendant(operator3);
        dispatcher.clockinAttendant(operator4);
        dispatcher.clockinAttendant(operator5);
        dispatcher.clockinAttendant(operator6);
        dispatcher.clockinAttendant(operator7);
        dispatcher.clockinAttendant(supervisor1);
        dispatcher.clockinAttendant(supervisor2);
        dispatcher.clockinAttendant(director1);

        Call call1 = new Call();
        Call call2 = new Call();
        Call call3 = new Call();
        Call call4 = new Call();
        Call call5 = new Call();
        Call call6 = new Call();
        Call call7 = new Call();
        Call call8 = new Call();
        Call call9 = new Call();
        Call call10 = new Call();
        Call call11 = new Call();

        service.submit(() -> this.emulateCall(dispatcher, call1));
        service.submit(() -> this.emulateCall(dispatcher, call2));
        service.submit(() -> this.emulateCall(dispatcher, call3));
        service.submit(() -> this.emulateCall(dispatcher, call4));
        service.submit(() -> this.emulateCall(dispatcher, call5));
        service.submit(() -> this.emulateCall(dispatcher, call6));
        service.submit(() -> this.emulateCall(dispatcher, call7));
        service.submit(() -> this.emulateCall(dispatcher, call8));
        service.submit(() -> this.emulateCall(dispatcher, call9));
        service.submit(() -> this.emulateCall(dispatcher, call10));
        service.submit(() -> this.emulateCall(dispatcher, call11));

        service.shutdown();
        service.awaitTermination(25, TimeUnit.SECONDS); // add some extra time to avoid race conditions

        assertEquals(Role.OPERATOR, call1.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call2.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call3.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call4.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call5.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call6.getAttendant().get().getRole());
        assertEquals(Role.OPERATOR, call7.getAttendant().get().getRole());
        assertEquals(Role.SUPERVISOR, call8.getAttendant().get().getRole());
        assertEquals(Role.SUPERVISOR, call9.getAttendant().get().getRole());
        assertEquals(Role.DIRECTOR, call10.getAttendant().get().getRole());
        assertEquals(Status.ENDED, call11.getStatus());
        assertTrue(call11.getDuration() >= 5000);
    }

    public void testDispatcherWithOneEmployeeAndTwoCalls() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(2); // numCalls

        Employee operator1 = new Employee(1, "Operator 1", Role.OPERATOR);

        Dispatcher dispatcher = new Dispatcher(10);

        dispatcher.clockinAttendant(operator1);

        Call call1 = new Call();
        Call call2 = new Call();

        service.submit(() -> this.emulateCall(dispatcher, call1));
        service.submit(() -> this.emulateCall(dispatcher, call2));

        service.shutdown();
        service.awaitTermination(25, TimeUnit.SECONDS); // add some extra time to avoid race conditions

        assertEquals(operator1, call1.getAttendant().get());
        assertEquals(operator1, call2.getAttendant().get());
    }

    public void testDispatcherWithoutEmployees() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(1); // numCalls

        Dispatcher dispatcher = new Dispatcher(10);

        Call call1 = new Call();

        service.submit(() -> this.emulateCall(dispatcher, call1));

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        assertFalse(call1.getAttendant().isPresent());
    }

    public void testCallDuration() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(1); // numCalls

        Employee operator1 = new Employee(1, "Operator 1", Role.OPERATOR);

        Dispatcher dispatcher = new Dispatcher(10);

        dispatcher.clockinAttendant(operator1);

        Call call1 = new Call();

        service.submit(() -> this.emulateCall(dispatcher, call1));

        service.shutdown();
        service.awaitTermination(15, TimeUnit.SECONDS); // add some extra time to avoid race conditions

        assertTrue(call1.getDuration() >= 5000 && call1.getDuration() < 10001);
    }

    public void testCallStatus() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(1); // numCalls

        Employee operator1 = new Employee(1, "Operator 1", Role.OPERATOR);

        Dispatcher dispatcher = new Dispatcher(10);

        dispatcher.clockinAttendant(operator1);

        Call call1 = new Call();

        assertEquals(Status.STANDBY, call1.getStatus());

        service.submit(() -> this.emulateCall(dispatcher, call1));

        service.shutdown();
        service.awaitTermination(15, TimeUnit.SECONDS); // add some extra time to avoid race conditions

        assertEquals(Status.ENDED, call1.getStatus());
    }

    public void testClockoutAttendant() throws InterruptedException
    {
        ExecutorService service = Executors.newFixedThreadPool(2); // numCalls

        Employee operator1 = new Employee(1, "Operator 1", Role.OPERATOR);
        Employee operator2 = new Employee(1, "Operator 2", Role.OPERATOR);

        Dispatcher dispatcher = new Dispatcher(10);

        dispatcher.clockinAttendant(operator1);
        dispatcher.clockinAttendant(operator2);
        dispatcher.clockoutAttendant(operator2);

        Call call1 = new Call();
        Call call2 = new Call();

        service.submit(() -> this.emulateCall(dispatcher, call1));
        service.submit(() -> this.emulateCall(dispatcher, call2));

        service.shutdown();
        service.awaitTermination(25, TimeUnit.SECONDS); // add some extra time to avoid race conditions

        assertEquals(operator1, call1.getAttendant().get());
        assertEquals(operator1, call2.getAttendant().get());
    }

    private void emulateCall(Dispatcher dispatcher, Call call) {
        dispatcher.dispatchCall(call);
        int duration = ThreadLocalRandom.current().nextInt(5000, 10001);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        call.setDuration(duration);
        dispatcher.endCall(call);
    }
}

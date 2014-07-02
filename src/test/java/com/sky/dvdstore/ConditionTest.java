/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.dvdstore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author alexp
 */
public class ConditionTest {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean flag = false;

    public ConditionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private void waitForCondition() {
        lock.lock();
        try {
            while (!flag) {
                condition.await();
            }
        } catch (InterruptedException ex) {

        } finally {
            lock.unlock();
        }
    }

    private void notifyWaiting() {
        lock.lock();
        try {
            if (!flag) {
                flag = true;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void testCondition() {
        Thread t1 = new Thread(new Runnable() {

            public void run() {
                System.out.println(Thread.currentThread().getName() + " waiting for condition!");
                waitForCondition();
                System.out.println(Thread.currentThread().getName() + " notified!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + " finished!");
            }
        });
        Thread t2 = new Thread(new Runnable() {

            public void run() {
                System.out.println(Thread.currentThread().getName() + " job!");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                notifyWaiting();
                System.out.println(Thread.currentThread().getName() + " finished!");
            }
        });
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        ConditionTest ct = new ConditionTest();
        ct.testCondition();
    }
}

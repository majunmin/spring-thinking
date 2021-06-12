package com.majm.demo;


/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 23:56
 * @since
 */

public class NaviWaiter implements Waiter {
    @Override
    public void greetTo(String clientName) {
        System.out.println("NaviWaiter greetTo: " + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaviWaiter serveTo: " + clientName);

    }
}

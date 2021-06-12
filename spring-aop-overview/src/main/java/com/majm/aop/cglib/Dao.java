package com.majm.aop.cglib;

/**
 * Dao demo 类 (target类) </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 13:38
 * @since
 */
public class Dao {

    public void select() {
        System.out.println("select * from t_adposition_info where id = 1");
    }

    public void insert() {
        System.out.println("insert into ...");
    }

    public final void delete() {
        System.out.println("delete from ...");
    }
}



package com.majm.domain;

/**
 *   </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 08:08
 * @since
 */
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}

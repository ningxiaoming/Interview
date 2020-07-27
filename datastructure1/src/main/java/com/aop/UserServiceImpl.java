package com.aop;

public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("add");
    }

    @Override
    public void del() {
        System.out.println("del");
    }

    @Override
    public void up() {
        System.out.println("up");

    }

    @Override
    public void query() {
        System.out.println("query");
    }
}

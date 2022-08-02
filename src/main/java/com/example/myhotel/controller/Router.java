package com.example.myhotel.controller;

public class Router {
    private String page;
    private Type actionType;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router() {
        this.actionType = Type.FORWARD;
        this.page = PagePath.SIGN_UP;
    }

    public Router(String page, Type actionType) {
        this.page = page;
        this.actionType = actionType;
    }

    public Router(String page) {
        this.actionType = Type.FORWARD;
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Type getActionType() {
        return actionType;
    }

    public void setActionType(Type actionType) {
        this.actionType = actionType;
    }
}

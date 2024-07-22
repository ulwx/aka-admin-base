package com.github.ulwx.aka.admin.filter;

public class F3AccessException extends RuntimeException{

    private Object data;
    public F3AccessException(String message,Object data) {
        super(message);
        this.data=data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.hehetenya.test_forms.exeptions;

public class DBException extends Exception{

    public DBException(String message, Throwable cause){
        super(message, cause);
    }

    public DBException(String message){
        super(message);
    }
}

package com.example.myhotel.exeption;

import java.sql.SQLException;

public class DaoException extends Exception {


    public DaoException(SQLException sqlException) {
    }

    public DaoException(String msg){
        super(msg);
    }

    public DaoException(String msg, Throwable cause){
        super(msg,cause);
    }
}

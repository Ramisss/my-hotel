package com.example.myhotel.mapper;

public interface Mapper<F, T> {
    // From To
    T mapFrom (F object);
}

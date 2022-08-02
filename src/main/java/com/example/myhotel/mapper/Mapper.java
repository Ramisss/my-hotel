package com.example.myhotel.mapper;

public interface Mapper<T, R> {

    R mapFrom(T object);
}

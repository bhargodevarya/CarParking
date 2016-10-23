package com.bhargo.service;

/**
 * Created by bhargo on 23/10/16.
 */
public interface Observer<T> {

    void invoke(T t);
}

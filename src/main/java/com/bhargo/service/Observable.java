package com.bhargo.service;

/**
 * Created by bhargo on 23/10/16.
 */
public interface Observable<T> {

    void register(T t);
    void unregister(T t);
}

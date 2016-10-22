package com.bhargo.service;

import com.bhargo.model.Request;

/**
 * Created by bhargo on 22/10/16.
 */
public interface QueuingService<T extends Request> {
    boolean Queue(T t);
}

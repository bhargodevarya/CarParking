package com.bhargo.service;

import com.bhargo.model.ExitRequest;

import java.util.concurrent.BlockingQueue;

/**
 * Created by bhargo on 22/10/16.
 */
public class ExitQueuingServiceImpl implements QueuingService<ExitRequest> {

    private final BlockingQueue<ExitRequest> parkingRequestBlockingQueue;

    public ExitQueuingServiceImpl(BlockingQueue<ExitRequest> parkingRequestBlockingQueue) {
        this.parkingRequestBlockingQueue = parkingRequestBlockingQueue;
    }

    @Override
    public boolean Queue(ExitRequest ExitRequest) {
        if(validate(ExitRequest)) {
            parkingRequestBlockingQueue.add(ExitRequest);
        }
        return false;
    }

    private boolean validate(ExitRequest ExitRequest) {
        return true;
    }}

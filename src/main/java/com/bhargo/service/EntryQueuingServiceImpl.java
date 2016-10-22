package com.bhargo.service;

import com.bhargo.model.EntryRequest;

import java.util.concurrent.BlockingQueue;

/**
 * Created by bhargo on 22/10/16.
 */
public class EntryQueuingServiceImpl implements QueuingService<EntryRequest> {

    private final BlockingQueue<EntryRequest> parkingRequestBlockingQueue;

    public EntryQueuingServiceImpl(BlockingQueue<EntryRequest> parkingRequestBlockingQueue) {
        this.parkingRequestBlockingQueue = parkingRequestBlockingQueue;
    }

    @Override
    public boolean Queue(EntryRequest entryRequest) {
        if(validate(entryRequest)) {
            try {
                parkingRequestBlockingQueue.put(entryRequest);
                System.out.println("Requesting parking for " + entryRequest);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean validate(EntryRequest entryRequest) {
        return true;
    }
}

package com.bhargo.model;

/**
 * Created by bhargo on 22/10/16.
 */
public class EntryRequest extends Request {

    public EntryRequest(String barricadeId) {
        super(barricadeId);
    }

    public EntryRequest(String barricadeId, String vehicleId) {
        super(barricadeId, vehicleId);
    }
}

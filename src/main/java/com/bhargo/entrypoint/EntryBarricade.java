package com.bhargo.entrypoint;

import com.bhargo.model.EntryRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bhargo on 21/10/16.
 */
public class EntryBarricade extends Thread {

    private String barricadeId;

    public String getBarricadeId() {
        return barricadeId;
    }

    public EntryBarricade(String barricadeId) {
        this.barricadeId = barricadeId;
    }

    public void setBarricadeId(String barricadeId) {
        this.barricadeId = barricadeId;
    }

    public EntryBarricade (String barricadeId, Runnable runnable) {
        super(runnable);
        this.barricadeId = barricadeId;
    }

    public EntryBarricade(Runnable target) {
        super(target);
    }

    public void parkAt(String string) {
        System.out.println("Received parking slot at  " + string);
    }
}

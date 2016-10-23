package com.bhargo.service;

import com.bhargo.entrypoint.EntryBarricade;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by bhargo on 23/10/16.
 */
public class EntryBarricadeService implements Observer<String>{

    private List<EntryBarricade> entryBarricades;
    private Runnable runnable;
    private Observable<EntryBarricade> observable;

    public EntryBarricadeService(List<EntryBarricade> entryBarricades, Runnable runnable, Observable observable) {
        this.entryBarricades = entryBarricades;
        this.runnable = runnable;
        this.observable = observable;
    }

    public EntryBarricadeService(List<EntryBarricade> entryBarricades) {
        this.entryBarricades = entryBarricades;
    }

    public EntryBarricadeService(List<EntryBarricade> entryBarricades, Observable<EntryBarricade> observable) {
        this.entryBarricades = entryBarricades;
        this.observable = observable;
    }

    public EntryBarricadeService(List<EntryBarricade> entryBarricades, Runnable runnable) {
        this.entryBarricades = entryBarricades;
        this.runnable = runnable;
    }

    public List<EntryBarricade> getEntryBarricades() {
        return entryBarricades;
    }

    public void setEntryBarricades(List<EntryBarricade> entryBarricades) {
        this.entryBarricades = entryBarricades;
    }

    @PostConstruct
    public void startEntry () {
        synchronized (this) {
            entryBarricades.forEach(n -> {
                n.start();
                observable.register(n);
            });
        }
    }

    @Override
    public void invoke(String string) {
        entryBarricades.stream().filter(n -> n.getName().equals(string))
                .findFirst().get().parkAt(string);
    }
}

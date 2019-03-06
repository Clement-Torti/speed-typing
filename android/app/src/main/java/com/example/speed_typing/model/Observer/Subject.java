package com.example.speed_typing.model.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Subject {
    private List<IObserver> observers = new ArrayList<>();

    public Subject() {}

    public void attach(IObserver obs) {
        observers.add(obs);
    }

    public boolean dettach(IObserver obs) {
        return observers.remove(obs);
    }

    protected void notifier() {
        for(IObserver obs: observers) {
            obs.update();
        }
    }

    protected void notifierChrono() {
        for(IObserver obs: observers) {
            obs.chronoUpdate();
        }
    }

}

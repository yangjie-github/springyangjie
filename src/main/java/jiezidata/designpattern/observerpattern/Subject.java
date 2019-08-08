package jiezidata.designpattern.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjie
 * @date 2019/1/31 7:55
 *
 * Subject 类
 */
public class Subject {

    private List<AbstractObserver> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(AbstractObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (AbstractObserver observer : observers) {
            observer.update();
        }
    }
}


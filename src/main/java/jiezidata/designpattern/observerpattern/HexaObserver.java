package jiezidata.designpattern.observerpattern;

/**
 * @author yangjie
 * @date 2019/1/31 7:59
 */
public class HexaObserver extends AbstractObserver {

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println(subject.getState());
    }
}


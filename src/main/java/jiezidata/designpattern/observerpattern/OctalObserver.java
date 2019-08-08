package jiezidata.designpattern.observerpattern;

/**
 * @author yangjie
 * @date 2019/1/31 7:58
 */
public class OctalObserver extends AbstractObserver {

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println(subject.getState());
    }
}


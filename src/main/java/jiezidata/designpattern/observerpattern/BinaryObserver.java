package jiezidata.designpattern.observerpattern;

/**
 * 创建实体观察者类
 *
 * @author yangjie
 * @date 2019/1/31 7:58
 */
public class BinaryObserver extends AbstractObserver {

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println(subject.getState());
    }
}


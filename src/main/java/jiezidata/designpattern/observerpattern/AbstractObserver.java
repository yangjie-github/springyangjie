package jiezidata.designpattern.observerpattern;

/**
 * AbstractObserver 类
 *
 * @author yangjie
 * @date 2019/1/31 7:56
 */

public abstract class AbstractObserver {

    protected Subject subject;

    /**
     * 更新状态
     */
    public abstract void update();
}


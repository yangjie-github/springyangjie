//package jiezidata.designpattern;
//
///**
// * @author yangjie
// * @date 2018/12/1 8:24
// * 单例模式确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
// * 在计算机系统中，线程池、缓存、日志对象、对话框、打印机、显卡的驱动程序对象常被设计成单例。
// * 这些应用都或多或少具有资源管理器的功能。每台计算机可以有若干个打印机，但只能有一个Printer Spooler，
// * 以避免两个打印作业同时输出到打印机中。每台计算机可以有若干通信端口，系统应当集中管理这些通信端口，以避免一个通信端口同时被两个请求同时调用。
// * 总之，选择单例模式就是为了避免不一致状态，避免政出多头。
// *
// *  1、单例类只能有一个实例。
// *  2、单例类必须自己创建自己的唯一实例。
// *  3、单例类必须给所有其他对象提供这一实例。
// */
//
//public class SingletonPattern {
//
//    /**
//     * 实现单例的五种方式：
//     * 饿汉式单例
//     *
//     * 饿汉式单例在类加载初始化时就创建好一个静态的对象供外部使用，除非系统重启，这个对象不会改变，所以本身就是线程安全的。
//     *
//     * Singleton通过将构造方法限定为private避免了类在外部被实例化，
//     * 在同一个虚拟机范围内，Singleton的唯一实例只能通过getInstance()方法访问。
//     * （事实上，通过Java反射机制是能够实例化构造方法为private的类的，那基本上会使所有的Java单例实现失效。
//     * 此问题在此处不做讨论，姑且闭着眼就认为反射机制不存在。）
//     */
//    public class Singleton1 {
//        // 私有构造
//        private Singleton1() {}
//
//        private static Singleton1 single = new Singleton1();
//
//        // 静态工厂方法
//        public static Singleton1 getInstance() {
//            return single;
//        }
//    }
//
//    /**
//     * 懒汉式单例
//     *
//     * 该示例虽然用延迟加载方式实现了懒汉式单例，但在多线程环境下会产生多个single对象，如何改造请看以下方式:
//     */
//    public class Singleton2 {
//
//        // 私有构造
//        private Singleton2() {}
//
//        private static Singleton2 single = null;
//
//        public static Singleton2 getInstance() {
//            if(single == null){
//                single = new Singleton2();
//            }
//            return single;
//        }
//    }
//
//    /**
//     * 懒汉式改造
//     *
//     * 使用synchronized同步锁
//     */
//    public class Singleton3 {
//        // 私有构造
//        private Singleton3() {}
//
//        private static Singleton3 single = null;
//
//        public static Singleton3 getInstance() {
//
//            // 等同于 synchronized public static Singleton3 getInstance()
//            synchronized(Singleton3.class){
//                // 注意：里面的判断是一定要加的，否则出现线程安全问题
//                if(single == null){
//                    single = new Singleton3();
//                }
//            }
//            return single;
//        }
//    }
//
//    /**
//     * 懒汉式改造，双重锁
//     *
//     * 使用双重检查进一步做了优化，可以避免整个方法被锁，只对需要锁的代码部分加锁，可以提高执行效率。
//     */
//    public class Singleton4 {
//        // 私有构造
//        private Singleton4() {}
//
//        private static Singleton4 single = null;
//
//        // 双重检查
//        public static Singleton4 getInstance() {
//            if (single == null) {
//                synchronized (Singleton4.class) {
//                    if (single == null) {
//                        single = new Singleton4();
//                    }
//                }
//            }
//            return single;
//        }
//    }
//
//    /**
//     * 静态内部类实现
//     *
//     * 静态内部类虽然保证了单例在多线程并发下的线程安全性，但是在遇到序列化对象时，默认的方式运行得到的结果就是多例的。这种情况不多做说明了，使用时请注意。
//     */
//    public class Singleton6 {
//        // 私有构造
//        private Singleton6() {}
//
//        // 静态内部类
//        private static class InnerObject{
//            private static Singleton6 single = new Singleton6();
//        }
//
//        public static Singleton6 getInstance() {
//            return InnerObject.single;
//        }
//    }
//
//    /**
//     * .static静态代码块实现
//     */
//    public class Singleton6 {
//
//        // 私有构造
//        private Singleton6() {}
//
//        private static Singleton6 single = null;
//
//        // 静态代码块
//        static{
//            single = new Singleton6();
//        }
//
//        public static Singleton6 getInstance() {
//            return single;
//        }
//    }
//
//    /**
//     * 内部类枚举实现
//     */
//    public class SingletonFactory {
//
//        // 内部枚举类
//        private enum EnmuSingleton{
//            Singleton;
//            private Singleton8 singleton;
//
//            //枚举类的构造方法在类加载是被实例化
//            private EnmuSingleton(){
//                singleton = new Singleton8();
//            }
//            public Singleton8 getInstance(){
//                return singleton;
//            }
//        }
//        public static Singleton8 getInstance() {
//            return EnmuSingleton.Singleton.getInstance();
//        }
//    }
//
//    class Singleton8{
//        public Singleton8(){}
//    }
//}

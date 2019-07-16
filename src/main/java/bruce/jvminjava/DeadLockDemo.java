package bruce.jvminjava;

public class DeadLockDemo {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    void deadLock() {
        // 使用匿名类来快速创建两个线程对象
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println("step 1: thread-1 got lock-1");
                    try {
                        // 在获取第二个锁之前休眠一秒，给第二个线程留足启动时间让它去获得其第一个锁
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    synchronized (lock2) {
                        System.out.println("step 2: thread-1 got lock-2");
                    }
                }
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println("step 1: thread-2 got lock-2");
                    try {
                        // 在获取第二个锁之前休眠一秒，给第二个线程留足启动时间让它去获得其第一个锁
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    synchronized (lock1) {
                        System.out.println("step 2: thread-2 got lock-1");
                    }
                }
            }
        });
        
        thread1.start();
        thread2.start();
    }
    
    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
}

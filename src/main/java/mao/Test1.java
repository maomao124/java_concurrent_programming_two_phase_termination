package mao;

/**
 * Project name(项目名称)：java并发编程_两阶段终止
 * Package(包名): mao
 * Class(类名): Test1
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/8/27
 * Time(创建时间)： 20:02
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test1
{
    public static void service()
    {
        System.out.println("----------------------------------");
        Runtime r = Runtime.getRuntime();
        float memory;
        memory = r.totalMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("JVM总内存：%.3fMB\n", memory);
        memory = r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf(" 空闲内存：%.3fMB\n", memory);
        memory = r.totalMemory() - r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("已使用的内存：%.4fMB\n", memory);
        System.out.println("----------------------------------");
    }

    public static void release()
    {
        System.out.println("释放资源");
    }

    public static Thread t1()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    Thread currentThread = Thread.currentThread();
                    if (currentThread.isInterrupted())
                    {
                        //被打断，料理后事，比如释放资源
                        release();
                        //结束循环
                        break;
                    }
                    //没有被打断
                    try
                    {
                        //睡眠
                        Thread.sleep(1000);
                        //无异常，执行业务
                        service();
                    }
                    catch (InterruptedException e)
                    {
                        //有异常，设置打断标记
                        currentThread.interrupt();
                    }

                }
            }
        });
        thread.start();
        return thread;
    }

    public static void main(String[] args) throws InterruptedException
    {
        Thread thread = t1();
        Thread.sleep(3000);
        thread.interrupt();
    }
}

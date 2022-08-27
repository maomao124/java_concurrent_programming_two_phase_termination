package mao;

/**
 * Project name(项目名称)：java并发编程_两阶段终止
 * Package(包名): mao
 * Class(类名): T
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/8/27
 * Time(创建时间)： 20:20
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class T
{
    /**
     * 启动一个支持两阶段终止的线程，不需要调用start方法
     *
     * @param threadName    线程的名称
     * @param intervals     调用service方法的间隔时间，单位为毫秒
     * @param threadService 业务
     * @return Thread对象
     */
    public static Thread startThread(String threadName, long intervals, ThreadService threadService)
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
                        threadService.release();
                        //结束循环
                        break;
                    }
                    //没有被打断
                    try
                    {
                        //睡眠
                        Thread.sleep(intervals);
                        //无异常，执行业务
                        threadService.service();
                    }
                    catch (Exception e)
                    {
                        //有异常，设置打断标记
                        currentThread.interrupt();
                    }
                }
            }
        }, threadName);
        thread.start();
        return thread;
    }

    /**
     * 启动一个支持两阶段终止的线程，不需要调用start方法
     *
     * @param intervals     调用service方法的间隔时间，单位为毫秒
     * @param threadService 业务
     * @return Thread对象
     */
    public static Thread startThread(long intervals, ThreadService threadService)
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
                        threadService.release();
                        //结束循环
                        break;
                    }
                    //没有被打断
                    try
                    {
                        //睡眠
                        Thread.sleep(intervals);
                        //无异常，执行业务
                        threadService.service();
                    }
                    catch (Exception e)
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

    /**
     * 启动一个支持两阶段终止的线程，不需要调用start方法。间隔时间默认为1秒
     *
     * @param threadService 业务
     * @return Thread对象
     */
    public static Thread startThread(ThreadService threadService)
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
                        threadService.release();
                        //结束循环
                        break;
                    }
                    //没有被打断
                    try
                    {
                        //睡眠
                        Thread.sleep(1000);
                        //无异常，执行业务
                        threadService.service();
                    }
                    catch (Exception e)
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
}

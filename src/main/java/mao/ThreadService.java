package mao;

/**
 * Project name(项目名称)：java并发编程_两阶段终止
 * Package(包名): mao
 * Interface(接口名): ThreadService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/8/27
 * Time(创建时间)： 20:20
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface ThreadService
{
    /**
     * 主业务
     *
     * @throws Exception 异常
     */
    void service() throws Exception;

    /**
     * 被打断后释放资源的业务
     */
    void release();
}

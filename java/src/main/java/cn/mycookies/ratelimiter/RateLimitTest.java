package cn.mycookies.ratelimiter;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 限流器测试
 *
 * @author Jann Lee
 * @date 2020-04-17 1:07
 **/
public class RateLimitTest {

    @Test
    public void testCounterRateLimiter() throws InterruptedException {
        CounterRateLimiter rateLimiter = new CounterRateLimiter(20);
        while (true) {
            boolean flag = rateLimiter.tryAcquire();
            long seconds = TimeUnit.SECONDS.toSeconds(rateLimiter.timestamp);
            if (flag) {
                System.out.println(seconds + "--------流量被放行--------");
                Thread.sleep(20);
            } else {
                System.out.println(seconds + "流量被限制");
                Thread.sleep((long) (Math.random() * 10 + 1));
            }
        }
    }


    @Test
    public void testTokenBucketRateLimiter() throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(1, 20);
        AtomicLong counter = new AtomicLong(0);
        while (true) {
            boolean flag = rateLimiter.tryAcquire();
            counter.getAndAdd(1);
            if (flag) {
                System.out.println(counter.get() + "--------流量被放行--------");
                Thread.sleep(1);
            } else {
                System.out.println(counter.get() + "流量被限制");
                Thread.sleep((long) (Math.random() * 10 + 1));
            }
        }
    }
}

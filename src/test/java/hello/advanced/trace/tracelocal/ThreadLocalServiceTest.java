package hello.advanced.trace.tracelocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000); // 로직이 끝나는데 2초걸리는데 스레드 sleep을 2초를걸어서 동시성 문제 발생X
//        sleep( 100); // 로직이 끝나는데 1초걸리는데 스레드 sleep을 0.2초를걸어서 동시성 문제 발생함
        threadB.start();

        sleep(3000); // 메인 스레드 종료 대기
        log.info("main exit");

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

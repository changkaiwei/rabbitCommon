package com.rabbit.api.util;

import com.rabbit.api.entity.RabbitMessage;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author kevin
 */
@Component
public class AsyncUtil {

    private static ExecutorService executorService =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),20,60, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>());

    public  void send(Runnable runnable){
        executorService.submit(runnable);
    }
}

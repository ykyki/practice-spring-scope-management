package com.example.practice.spring.scope.management.domain.wait;

import com.example.practice.spring.scope.management.domain.common.random.RandomUtilService;
import com.example.practice.spring.scope.management.mvc.api.util.async.AsyncWrapper;
import com.example.practice.spring.scope.management.mvc.util.request.mutable.RequestEventMutableEventDateTimeSetter;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WaitApiService {
    private final RandomUtilService randomUtilService;

    private final AsyncWrapper asyncWrapper;

    private final RequestEventMutableEventDateTimeSetter requestEventMutableEventDateTimeSetter;

    private final static int RANDOM_TIME = 987;
    private final static int HIT_PERCENTAGE = 3;

    public String waitRandomTime() {
        var randomTime = randomUtilService.naturalNumber(RANDOM_TIME);

        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (randomUtilService.hit(HIT_PERCENTAGE)) {
            throw new RuntimeException("Unlucky random error");
        }

        return String.format("%04d ms", randomTime);
    }

    public List<String> waitParallel() {
        var number = 10;

        java.util.List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            requestEventMutableEventDateTimeSetter.setCurrentDateTime();

            futures.add(
                    asyncWrapper.call(this::waitRandomTime)
            );

            // delay
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread sleep error");
            }
        }
        requestEventMutableEventDateTimeSetter.unset();

        // wait for all to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        return futures
                .stream()
                .map(CompletableFuture::join)
                .collect(List.collector());
    }
}

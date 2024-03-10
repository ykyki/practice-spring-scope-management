package com.example.practice.spring.scope.management.domain.wait;

import com.example.practice.spring.scope.management.mvc.api.util.async.AsyncWrapper;
import com.example.practice.spring.scope.management.mvc.util.request.mutable.RequestEventMutableEventDateTimeSetter;
import com.example.practice.spring.scope.management.util.random.RandomUtil;
import com.example.practice.spring.scope.management.util.sleep.SleepUtil;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WaitApiService {
    private final RandomUtil randomUtil;

    private final SleepUtil sleepUtil;

    private final AsyncWrapper asyncWrapper;

    private final RequestEventMutableEventDateTimeSetter requestEventMutableEventDateTimeSetter;

    private final static int RANDOM_TIME = 987;
    private final static int HIT_PERCENTAGE = 3;

    public String waitRandomTime() {
        var randomTime = randomUtil.naturalNumber(RANDOM_TIME);

        sleepUtil.sleep(randomTime);

        if (randomUtil.hit(HIT_PERCENTAGE)) {
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
            sleepUtil.sleep(10);
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

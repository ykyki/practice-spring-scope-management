package com.example.practice.spring.scope.management.domain.wait;

import com.example.practice.spring.scope.management.mvc.api.util.async.AsyncWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JustWaitService {
    private final AsyncWrapper asyncWrapper;

    public String waitRandomTime() {
        var randomTime = (int) (Math.random() * 1_000);

        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var p = 5; // chance of throwing an error
        if (Math.random() * 100 + 1 < p) {
            throw new RuntimeException("Unlucky random error");
        }

        return String.format("%04d ms", randomTime);
    }

    public String waitParallel() {
        var number = 10;

        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            futures.add(
                    asyncWrapper.call(this::waitRandomTime)
            );

            // delay
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // wait for all to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        var results =
                futures.stream()
                        .map(CompletableFuture::join)
                        .toArray(String[]::new);

        return String.join(", ", results);
    }
}

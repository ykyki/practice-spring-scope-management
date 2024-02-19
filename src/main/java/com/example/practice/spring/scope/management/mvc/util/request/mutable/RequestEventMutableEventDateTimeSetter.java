package com.example.practice.spring.scope.management.mvc.util.request.mutable;

import com.example.practice.spring.scope.management.mvc.util.request.RequestEventDateTime;
import io.vavr.control.Option;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class RequestEventMutableEventDateTimeSetter {

    @Getter
    private Option<RequestEventDateTime> requestEventDateTimeOption;

    public RequestEventMutableEventDateTimeSetter() {
        this.requestEventDateTimeOption = Option.none();
    }

    public void setCurrentDateTime() {
        // TODO RequestEventDateTimeのコンストラクタ依存の実装にしないようにする
        this.requestEventDateTimeOption = Option.of(new RequestEventDateTime());
    }

    public void unset() {
        this.requestEventDateTimeOption = Option.none();
    }
}

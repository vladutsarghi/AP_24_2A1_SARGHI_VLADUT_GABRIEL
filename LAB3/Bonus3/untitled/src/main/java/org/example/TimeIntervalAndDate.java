package org.example;

import java.time.LocalDate;

public class TimeIntervalAndDate<T1, T2, T3>{
    private T1 date;
    private final T2 start;
    private final T3 finish;
    public TimeIntervalAndDate(T1 date, T2 start, T3 finish){
        this.date = date;
        this.start = start;
        this.finish = finish;
    }

    public TimeIntervalAndDate(T2 start, T3 finish){
        this.start = start;
        this.finish = finish;
    }

    public T1 getDate() {
        return date;
    }

    public T2 getStart() {
        return start;
    }

    public T3 getFinish() {
        return finish;
    }
}

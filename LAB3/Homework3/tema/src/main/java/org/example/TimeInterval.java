package org.example;

import java.sql.Time;

public class TimeInterval<T1, T2>{
    private final T1 start;
    private final T2 finish;
    public TimeInterval(T1 start, T2 finish){
        this.start = start;
        this.finish = finish;
    }

    public T1 getStart() {
        return start;
    }

    public T2 getFinish() {
        return finish;
    }
}

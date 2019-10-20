package com.test.participant;

import java.util.function.Function;

public enum Event {
    Sprint100m(true, Double::valueOf),
    LongJump(false, (x) -> Double.valueOf(x) * 100),
    ShotPut(false, Double::valueOf),
    HighJump(false, (x) -> Double.valueOf(x) * 100),
    Sprint400m(true, Double::valueOf),
    Sprint110mHurdles(true, Double::valueOf),
    DiscusThrow(false, Double::valueOf),
    PoleVault(false, (x) -> Double.valueOf(x) * 100),
    JavelinThrow(false, Double::valueOf),
    Run1500m(true, (x) -> {
        String[] a = x.split("\\.");
        return Double.valueOf(a[0]) * 60 + Double.valueOf(a[1] + "." + a[2]);
    });

    private boolean isTrackEvent;
    private Function<String, Double> converter;

    Event(boolean isTrackEvent, Function<String, Double> converter) {
        this.isTrackEvent = isTrackEvent;
        this.converter = converter;
    }

    public boolean isTrackEvent() {
        return isTrackEvent;
    }

    public Function<String, Double> getConverter() {
        return converter;
    }
}

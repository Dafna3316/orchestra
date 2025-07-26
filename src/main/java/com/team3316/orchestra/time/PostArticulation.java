package com.team3316.orchestra.time;

import java.util.List;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PostArticulation {
    @Contract(pure = true)
    @NotNull
    List<TimedNote<Double>> postTransform(@NotNull TimedNote<Double> original);
}

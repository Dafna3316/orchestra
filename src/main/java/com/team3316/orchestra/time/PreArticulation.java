package com.team3316.orchestra.time;

import java.util.List;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Pitch;

@FunctionalInterface
public interface PreArticulation {
    @Contract(pure = true)
    @NotNull
    List<TimedNote<Pitch>> preTransform(@NotNull TimedNote<Pitch> original);
}

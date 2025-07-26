package com.team3316.orchestra.time;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.Pitch;

public final class Articulations {
    public static final Articulation STACCATO = new Staccato();

    private static final class Staccato implements Articulation {
        @Override
        public @NotNull List<TimedNote<Double>> postTransform(@NotNull TimedNote<Double> original) {
            List<TimedNote<Double>> list = new ArrayList<>();
            list.add(new TimedNote<>(original.pitch(), original.duration().divide(2)));
            list.add(new TimedNote<>(Double.valueOf(0), original.duration().divide(2)));

            return list;
        }

        @Override
        public @NotNull List<TimedNote<Pitch>> preTransform(@NotNull TimedNote<Pitch> original) {
            List<TimedNote<Pitch>> list = new ArrayList<>();
            list.add(new TimedNote<Pitch>(original.pitch(), original.duration().divide(2)));
            list.add(new TimedNote<Pitch>(Pitch.of(0.0), original.duration().divide(2)));

            return list;
        }
    }

    private Articulations() { throw new UnsupportedOperationException(); }
}

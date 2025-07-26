package com.team3316.orchestra.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Voice(TimedFrequency[] frequencies) {
    public static Voice of(List<TimedFrequency> frequencies) {
        return FrequencyBuilder.of(frequencies).build();
    }

    public static Voice of(TimedFrequency[] frequencies) {
        return new Voice(frequencies);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(int capacity) {
        return new Builder(capacity);
    }

    // MUTABLE
    public static final class Builder {
        private ArrayList<TimedPitch> pitches;

        public Builder(List<TimedPitch> pitches) {
            this.pitches = new ArrayList<>(pitches);
        }
        
        public Builder(int capacity) {
            this.pitches = new ArrayList<>(capacity);
        }

        public Builder() {
            this.pitches = new ArrayList<>();
        }

        public Builder add(TimedPitch pitch) {
            pitches.add(pitch);
            return this;
        }

        public Builder add(TimedPitch[] pitches) {
            this.pitches.addAll(Arrays.asList(pitches));
            return this;
        }

        public Builder add(List<TimedPitch> pitches) {
            this.pitches.addAll(pitches);
            return this;
        }

        public FrequencyBuilder frequencies() {
            return new FrequencyBuilder(pitches
                .stream()
                .map(TimedPitch::toTimedFrequency)
                .collect(Collectors.toCollection(() -> new ArrayList<>(pitches.size())))
            );
        }

        public Voice build() {
            TimedFrequency[] tfs = new TimedFrequency[pitches.size()];
            int i = 0;
            for (final var tp : pitches) {
                tfs[i++] = tp.toTimedFrequency();
            }
            return new Voice(tfs);
        }

        public static Builder of(List<TimedPitch> pitches) {
            return new Builder(pitches);
        }

        public static Builder of(TimedPitch[] pitches) {
            return new Builder(Arrays.asList(pitches));
        }
    }

    public static final class FrequencyBuilder {
        private ArrayList<TimedFrequency> frequencies;
        private static final TimedFrequency[] TF_ARRAY = new TimedFrequency[0];

        FrequencyBuilder(ArrayList<TimedFrequency> frequencies) {
            this.frequencies = frequencies;
        }

        public FrequencyBuilder(List<TimedFrequency> frequencies) {
            this.frequencies = new ArrayList<>(frequencies);
        }

        public FrequencyBuilder(int capacity) {
            this.frequencies = new ArrayList<>(capacity);
        }

        public FrequencyBuilder() {
            this.frequencies = new ArrayList<>();
        }

        public FrequencyBuilder add(TimedFrequency frequency) {
            frequencies.add(frequency);
            return this;
        }

        public FrequencyBuilder add(TimedFrequency[] frequencies) {
            this.frequencies.addAll(Arrays.asList(frequencies));
            return this;
        }

        public FrequencyBuilder add(List<TimedFrequency> frequencies) {
            this.frequencies.addAll(frequencies);
            return this;
        }

        public Voice build() {
            return new Voice(frequencies.toArray(TF_ARRAY));
        }

        public static FrequencyBuilder of(List<TimedFrequency> frequencies) {
            return new FrequencyBuilder(frequencies);
        }

        public static FrequencyBuilder of(TimedFrequency[] frequencies) {
            return new FrequencyBuilder(Arrays.asList(frequencies));
        }
    }
}

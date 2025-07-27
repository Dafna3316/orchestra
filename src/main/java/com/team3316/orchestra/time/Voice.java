package com.team3316.orchestra.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.numbers.fraction.Fraction;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * List of timed frequencies.
 * @param frequencies Frequencies
 */
public record Voice(TimedFrequency[] frequencies) implements Supplier<Voice> {
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

    @Override
    public Voice get() {
        return this;
    }

    public static final class NoteBuilder implements Supplier<Voice> {
        private ArrayList<Timed<NamedNote>> notes;
        private TuningSystem sys;

        public NoteBuilder(List<Timed<NamedNote>> notes, TuningSystem sys) {
            this.notes = new ArrayList<>(notes);
            this.sys = sys;
        }

        public NoteBuilder(int capacity, TuningSystem sys) {
            this.notes = new ArrayList<>(capacity);
            this.sys = sys;
        }

        public NoteBuilder(TuningSystem sys) {
            this.notes = new ArrayList<>();
            this.sys = sys;
        }

        public NoteBuilder add(Timed<NamedNote> note) {
            notes.add(note);
            return this;
        }

        public NoteBuilder add(Timed<NamedNote>[] notes) {
            this.notes.addAll(Arrays.asList(notes));
            return this;
        }

        public NoteBuilder add(List<Timed<NamedNote>> notes) {
            this.notes.addAll(notes);
            return this;
        }

        public NoteBuilder scaled(Fraction scalar, Timed<NamedNote> note) {
            notes.add(new Timed<>(note.pitch(), note.duration().multiply(scalar)));
            return this;
        }

        public NoteBuilder scaled(Fraction scalar, Timed<NamedNote>[] notes) {
            this.notes.addAll(Arrays.stream(notes).map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        public NoteBuilder scaled(Fraction scalar, List<Timed<NamedNote>> notes) {
            this.notes.addAll(notes.stream().map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        public NoteBuilder withTuningSystem(TuningSystem sys) {
            this.sys = sys;
            return this;
        }

        public FrequencyBuilder frequencies() {
            return new FrequencyBuilder(notes
                .stream()
                .map(p -> Timed.toFrequency(p, sys))
                .collect(Collectors.toCollection(() -> new ArrayList<>(notes.size())))
            );
        }

        public Builder pitches() {
            return new Builder(notes
                .stream()
                .map(p -> Timed.toPitch(p, sys))
                .collect(Collectors.toCollection(() -> new ArrayList<>(notes.size())))
            );
        }

        public Voice build() {
            TimedFrequency[] tfs = new TimedFrequency[notes.size()];
            int i = 0;
            for (final var tp : notes) {
                tfs[i++] = Timed.toFrequency(tp, sys);
            }
            return new Voice(tfs);
        }

        public static NoteBuilder of(List<Timed<NamedNote>> notes, TuningSystem sys) {
            return new NoteBuilder(notes, sys);
        }

        public static NoteBuilder of(Timed<NamedNote>[] notes, TuningSystem sys) {
            return new NoteBuilder(Arrays.asList(notes), sys);
        }

        @Override
        public Voice get() {
            return build();
        }
    }

    // MUTABLE
    public static final class Builder implements Supplier<Voice> {
        private ArrayList<Timed<Pitch>> pitches;

        Builder(ArrayList<Timed<Pitch>> pitches) {
            this.pitches = pitches;
        }

        public Builder(List<Timed<Pitch>> pitches) {
            this.pitches = new ArrayList<>(pitches);
        }
        
        public Builder(int capacity) {
            this.pitches = new ArrayList<>(capacity);
        }

        public Builder() {
            this.pitches = new ArrayList<>();
        }

        public Builder add(Timed<Pitch> pitch) {
            pitches.add(pitch);
            return this;
        }

        public Builder add(Timed<Pitch>[] pitches) {
            this.pitches.addAll(Arrays.asList(pitches));
            return this;
        }

        public Builder add(List<Timed<Pitch>> pitches) {
            this.pitches.addAll(pitches);
            return this;
        }

        public Builder scaled(Fraction scalar, Timed<Pitch> pitch) {
            pitches.add(new Timed<>(pitch.pitch(), pitch.duration().multiply(scalar)));
            return this;
        }

        public Builder scaled(Fraction scalar, Timed<Pitch>[] pitches) {
            this.pitches.addAll(Arrays.stream(pitches).map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        public Builder scaled(Fraction scalar, List<Timed<Pitch>> pitches) {
            this.pitches.addAll(pitches.stream().map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        public FrequencyBuilder frequencies() {
            return new FrequencyBuilder(pitches
                .stream()
                .map(Timed::toFrequency)
                .collect(Collectors.toCollection(() -> new ArrayList<>(pitches.size())))
            );
        }

        public Voice build() {
            TimedFrequency[] tfs = new TimedFrequency[pitches.size()];
            int i = 0;
            for (final var tp : pitches) {
                tfs[i++] = Timed.toFrequency(tp);
            }
            return new Voice(tfs);
        }

        public static Builder of(List<Timed<Pitch>> pitches) {
            return new Builder(pitches);
        }

        public static Builder of(Timed<Pitch>[] pitches) {
            return new Builder(Arrays.asList(pitches));
        }

        @Override
        public Voice get() {
            return build();
        }
    }

    public static final class FrequencyBuilder implements Supplier<Voice> {
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

        public FrequencyBuilder scaled(Fraction scalar, TimedFrequency frequency) {
            frequencies.add(new TimedFrequency(frequency.frequency(), frequency.duration().multiply(scalar)));
            return this;
        }

        public FrequencyBuilder scaled(Fraction scalar, TimedFrequency[] frequencies) {
            this.frequencies.addAll(Arrays.stream(frequencies).map(p -> new TimedFrequency(p.frequency(), p.duration().multiply(scalar))).toList());
            return this;
        }

        public FrequencyBuilder scaled(Fraction scalar, List<TimedFrequency> frequencies) {
            this.frequencies.addAll(frequencies.stream().map(p -> new TimedFrequency(p.frequency(), p.duration().multiply(scalar))).toList());
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

        @Override
        public Voice get() {
            return build();
        }
    }
}

package com.team3316.orchestra.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;

/**
 * List of timed frequencies.
 * @param frequencies Frequencies
 */
public record Voice(@NotNull TimedFrequency[] frequencies) implements Supplier<Voice> {
    /**
     * Create a voice builder.
     * @return New builder
     */
    public static @NotNull Builder builder() {
        return new Builder();
    }

    /**
     * Create a voice builder with initial capacit.
     * @param capacity Initial capacity
     * @return New builder
     */
    public static @NotNull Builder builder(int capacity) {
        return new Builder(capacity);
    }

    @Override
    public Voice get() {
        return this;
    }

    /**
     * Builder that accumulates {@link NamedNote}s.
     */
    public static final class NoteBuilder implements Supplier<Voice> {
        private ArrayList<Timed<NamedNote>> notes;
        private TuningSystem sys;

        /**
         * Construct a new builder.
         * @param notes Initial list of named notes
         * @param sys Tuning system
         */
        public NoteBuilder(@NotNull List<Timed<NamedNote>> notes, @NotNull TuningSystem sys) {
            this.notes = new ArrayList<>(notes);
            this.sys = sys;
        }

        /**
         * Construct a new builder with initial capacity.
         * @param capacity Initial capacity
         * @param sys Tuning system
         */
        public NoteBuilder(int capacity, @NotNull TuningSystem sys) {
            this.notes = new ArrayList<>(capacity);
            this.sys = sys;
        }

        /**
         * Construct an empty builder.
         * @param sys Tuning system
         */
        public NoteBuilder(@NotNull TuningSystem sys) {
            this.notes = new ArrayList<>();
            this.sys = sys;
        }

        /**
         * Add a new note.
         * @param note Note to add
         * @return This builder (mutated)
         */
        @Contract(value = "!null->this", mutates="this")
        public NoteBuilder add(@NotNull Timed<NamedNote> note) {
            notes.add(note);
            return this;
        }

        /**
         * Add some notes.
         * @param notes Notes to add
         * @return This builder (mutated)
         */
        @Contract(value = "!null->this", mutates="this")
        public NoteBuilder add(@NotNull Timed<NamedNote>[] notes) {
            this.notes.addAll(Arrays.asList(notes));
            return this;
        }

        /**
         * Add some notes.
         * @param notes Notes to add
         * @return This builder (mutated)
         */
        @Contract(value = "!null->this", mutates="this")
        public NoteBuilder add(@NotNull List<Timed<NamedNote>> notes) {
            this.notes.addAll(notes);
            return this;
        }

        /**
         * Add a note and scale its duration.
         * @param scalar Factor to scale (2/3 to create a triplet)
         * @param note Note to add
         * @return This builder (mutated)
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public NoteBuilder scaled(@NotNull Fraction scalar, @NotNull Timed<NamedNote> note) {
            notes.add(new Timed<>(note.pitch(), note.duration().multiply(scalar)));
            return this;
        }

        /**
         * Add some notes and scale their durations.
         * @param scalar Factor to scale (2/3 to create a triplet)
         * @param notes Notes to add
         * @return This builder (mutated)
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public NoteBuilder scaled(@NotNull Fraction scalar, @NotNull Timed<NamedNote>[] notes) {
            this.notes.addAll(Arrays.stream(notes).map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        /**
         * Add some notes and scale their durations.
         * @param scalar Factor to scale (2/3 to create a triplet)
         * @param notes Notes to add
         * @return This builder (mutated)
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public NoteBuilder scaled(@NotNull Fraction scalar, @NotNull List<Timed<NamedNote>> notes) {
            this.notes.addAll(notes.stream().map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        /**
         * Modify the tuning system.
         * @param sys New tuning system
         * @return This builder (modified)
         */
        @Contract(value = "!null->this", mutates="this")
        public NoteBuilder withTuningSystem(@NotNull TuningSystem sys) {
            this.sys = sys;
            return this;
        }

        /**
         * Convert to a {@link FrequencyBuilder}.
         * @return New frequency builder
         */
        @Contract(pure = true)
        public @NotNull FrequencyBuilder frequencies() {
            return new FrequencyBuilder(notes
                .stream()
                .map(p -> Timed.toFrequency(p, sys))
                .collect(Collectors.toCollection(() -> new ArrayList<>(notes.size())))
            );
        }

        /**
         * Convert to a pitch {@link Builder}.
         * @return New pitch builder
         */
        @Contract(pure = true)
        public @NotNull Builder pitches() {
            return new Builder(notes
                .stream()
                .map(p -> Timed.toPitch(p, sys))
                .collect(Collectors.toCollection(() -> new ArrayList<>(notes.size())))
            );
        }

        /**
         * Build a voice.
         * @return New voice
         */
        @Contract(pure = true)
        public @NotNull Voice build() {
            TimedFrequency[] tfs = new TimedFrequency[notes.size()];
            int i = 0;
            for (final var tp : notes) {
                tfs[i++] = Timed.toFrequency(tp, sys);
            }
            return new Voice(tfs);
        }

        /**
         * Create a new builder.
         * @param notes Initial notes
         * @param sys Tuning system
         * @return New builder
         */
        public static @NotNull NoteBuilder of(@NotNull List<Timed<NamedNote>> notes, @NotNull TuningSystem sys) {
            return new NoteBuilder(notes, sys);
        }

        /**
         * Create a new builder.
         * @param notes Initial notes
         * @param sys Tuning system
         * @return New builder
         */
        public static @NotNull NoteBuilder of(@NotNull Timed<NamedNote>[] notes, @NotNull TuningSystem sys) {
            return new NoteBuilder(Arrays.asList(notes), sys);
        }

        @Override
        public Voice get() {
            return build();
        }
    }

    // MUTABLE
    /**
     * Builder that accumulates {@link Pitch}es.
     * @see NoteBuilder
     */
    public static final class Builder implements Supplier<Voice> {
        private ArrayList<Timed<Pitch>> pitches;

        Builder(ArrayList<Timed<Pitch>> pitches) {
            this.pitches = pitches;
        }

        /**
         * Create a new builder.
         * @param pitches As in {@link NoteBuilder#NoteBuilder(List, TuningSystem)}
         */
        public Builder(@NotNull List<Timed<Pitch>> pitches) {
            this.pitches = new ArrayList<>(pitches);
        }

        /**
         * Create a new builder.
         * @param capacity As in {@link NoteBuilder#NoteBuilder(int, TuningSystem)}
         */
        public Builder(int capacity) {
            this.pitches = new ArrayList<>(capacity);
        }

        /**
         * Create a new builder.
         * @see NoteBuilder#NoteBuilder(TuningSystem)
         */
        public Builder() {
            this.pitches = new ArrayList<>();
        }

        /**
         * Add a new pitch.
         * @param pitch As in {@link NoteBuilder#add(Timed)}
         * @return As in {@link NoteBuilder#add(Timed)}
         */
        @Contract(value = "!null->this", mutates="this")
        public Builder add(@NotNull Timed<Pitch> pitch) {
            pitches.add(pitch);
            return this;
        }

        /**
         * Add some pitches.
         * @param pitches As in {@link NoteBuilder#add(Timed[])}
         * @return As in {@link NoteBuilder#add(Timed[])}
         */
        @Contract(value = "!null->this", mutates="this")
        public Builder add(@NotNull Timed<Pitch>[] pitches) {
            this.pitches.addAll(Arrays.asList(pitches));
            return this;
        }

        /**
         * Add some pitches.
         * @param pitches As in {@link NoteBuilder#add(List)}
         * @return As in {@link NoteBuilder#add(List)}
         */
        @Contract(value = "!null->this", mutates="this")
        public Builder add(@NotNull List<Timed<Pitch>> pitches) {
            this.pitches.addAll(pitches);
            return this;
        }

        /**
         * Add a scaled pitch.
         * @param scalar As in {@link NoteBuilder#scaled(Fraction, Timed)}
         * @param pitch As in {@link NoteBuilder#scaled(Fraction, Timed)}
         * @return As in {@link NoteBuilder#scaled(Fraction, Timed)}
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public Builder scaled(@NotNull Fraction scalar, @NotNull Timed<Pitch> pitch) {
            pitches.add(new Timed<>(pitch.pitch(), pitch.duration().multiply(scalar)));
            return this;
        }

        /**
         * Add some scaled pitches.
         * @param scalar As in {@link NoteBuilder#scaled(Fraction, Timed[])}
         * @param pitches As in {@link NoteBuilder#scaled(Fraction, Timed[])}
         * @return As in {@link NoteBuilder#scaled(Fraction, Timed[])}
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public Builder scaled(@NotNull Fraction scalar, @NotNull Timed<Pitch>[] pitches) {
            this.pitches.addAll(Arrays.stream(pitches).map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        /**
         * Add some scaled pitches.
         * @param scalar As in {@link NoteBuilder#scaled(Fraction, List)}
         * @param pitches As in {@link NoteBuilder#scaled(Fraction, List)}
         * @return As in {@link NoteBuilder#scaled(Fraction, List)}
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public Builder scaled(@NotNull Fraction scalar, @NotNull List<Timed<Pitch>> pitches) {
            this.pitches.addAll(pitches.stream().map(p -> new Timed<>(p.pitch(), p.duration().multiply(scalar))).toList());
            return this;
        }

        /**
         * Convert to a frequency builder.
         * @return New builder
         * @see NoteBuilder#frequencies()
         */
        public @NotNull FrequencyBuilder frequencies() {
            return new FrequencyBuilder(pitches
                .stream()
                .map(Timed::toFrequency)
                .collect(Collectors.toCollection(() -> new ArrayList<>(pitches.size())))
            );
        }

        /**
         * Build a voice
         * @return New voice
         * @see NoteBuilder#build()
         */
        public @NotNull Voice build() {
            TimedFrequency[] tfs = new TimedFrequency[pitches.size()];
            int i = 0;
            for (final var tp : pitches) {
                tfs[i++] = Timed.toFrequency(tp);
            }
            return new Voice(tfs);
        }

        /**
         * Create a new builder.
         * @param pitches As in {@link NoteBuilder#of(List, TuningSystem)}
         * @return As in {@link NoteBuilder#of(List, TuningSystem)}
         */
        public static @NotNull Builder of(@NotNull List<Timed<Pitch>> pitches) {
            return new Builder(pitches);
        }

        /**
         * Create a new builder.
         * @param pitches As in {@link NoteBuilder#of(Timed[], TuningSystem)}
         * @return As in {@link NoteBuilder#of(Timed[], TuningSystem)}
         */
        public static @NotNull Builder of(@NotNull Timed<Pitch>[] pitches) {
            return new Builder(Arrays.asList(pitches));
        }

        @Override
        public Voice get() {
            return build();
        }
    }

    /**
     * A builder that accumulates {@link TimedFrequency TimedFrequencies}.
     */
    public static final class FrequencyBuilder implements Supplier<Voice> {
        private ArrayList<TimedFrequency> frequencies;
        private static final TimedFrequency[] TF_ARRAY = new TimedFrequency[0];

        FrequencyBuilder(ArrayList<TimedFrequency> frequencies) {
            this.frequencies = frequencies;
        }

        /**
         * Create a new builder.
         * @param frequencies As in {@link NoteBuilder#NoteBuilder(List, TuningSystem)}
         */
        public FrequencyBuilder(@NotNull List<TimedFrequency> frequencies) {
            this.frequencies = new ArrayList<>(frequencies);
        }

        /**
         * Create a new builder.
         * @param capacity As in {@link NoteBuilder#NoteBuilder(int, TuningSystem)}
         */
        public FrequencyBuilder(int capacity) {
            this.frequencies = new ArrayList<>(capacity);
        }

        /**
         * Create a new builder.
         * @see NoteBuilder#NoteBuilder(TuningSystem)
         */
        public FrequencyBuilder() {
            this.frequencies = new ArrayList<>();
        }

        /**
         * Add a new frequency.
         * @param frequency As in {@link NoteBuilder#add(Timed)}
         * @return As in {@link NoteBuilder#add(Timed)}
         */
        @Contract(value = "!null->this", mutates="this")
        public FrequencyBuilder add(@NotNull TimedFrequency frequency) {
            frequencies.add(frequency);
            return this;
        }

        /**
         * Add some frequencies.
         * @param frequencies As in {@link NoteBuilder#add(Timed[])}
         * @return As in {@link NoteBuilder#add(Timed[])}
         */
        @Contract(value = "!null->this", mutates="this")
        public FrequencyBuilder add(@NotNull TimedFrequency[] frequencies) {
            this.frequencies.addAll(Arrays.asList(frequencies));
            return this;
        }

        /**
         * Add some frequencies.
         * @param frequencies As in {@link NoteBuilder#add(List)}
         * @return As in {@link NoteBuilder#add(List)}
         */
        @Contract(value = "!null->this", mutates="this")
        public FrequencyBuilder add(@NotNull List<TimedFrequency> frequencies) {
            this.frequencies.addAll(frequencies);
            return this;
        }

        /**
         * Add a scaled frequency.
         * @param scalar As in {@link NoteBuilder#scaled(Fraction, Timed)}
         * @param frequency As in {@link NoteBuilder#scaled(Fraction, Timed)}
         * @return As in {@link NoteBuilder#scaled(Fraction, Timed)}
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public FrequencyBuilder scaled(@NotNull Fraction scalar, @NotNull TimedFrequency frequency) {
            frequencies.add(new TimedFrequency(frequency.frequency(), frequency.duration().multiply(scalar)));
            return this;
        }

        /**
         * Add some scaled frequencies.
         * @param scalar As in {@link NoteBuilder#scaled(Fraction, Timed[])}
         * @param frequencies As in {@link NoteBuilder#scaled(Fraction, Timed[])}
         * @return As in {@link NoteBuilder#scaled(Fraction, Timed[])}
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public FrequencyBuilder scaled(@NotNull Fraction scalar, @NotNull TimedFrequency[] frequencies) {
            this.frequencies.addAll(Arrays.stream(frequencies).map(p -> new TimedFrequency(p.frequency(), p.duration().multiply(scalar))).toList());
            return this;
        }

        /**
         * Add some scaled frequencies.
         * @param scalar As in {@link NoteBuilder#scaled(Fraction, List)}
         * @param frequencies As in {@link NoteBuilder#scaled(Fraction, List)}
         * @return As in {@link NoteBuilder#scaled(Fraction, List)}
         */
        @Contract(value = "!null,!null->this", mutates="this")
        public FrequencyBuilder scaled(@NotNull Fraction scalar, @NotNull List<TimedFrequency> frequencies) {
            this.frequencies.addAll(frequencies.stream().map(p -> new TimedFrequency(p.frequency(), p.duration().multiply(scalar))).toList());
            return this;
        }

        /**
         * Build a voice.
         * @return New voice
         */
        public @NotNull Voice build() {
            return new Voice(frequencies.toArray(TF_ARRAY));
        }

        /**
         * Create a new builder.
         * @param frequencies As in {@link NoteBuilder#of(List, TuningSystem)}
         * @return As in {@link NoteBuilder#of(List, TuningSystem)}
         */
        public static @NotNull FrequencyBuilder of(@NotNull List<TimedFrequency> frequencies) {
            return new FrequencyBuilder(frequencies);
        }

        /**
         * Create a new builder.
         * @param frequencies As in {@link NoteBuilder#of(Timed[], TuningSystem)}
         * @return As in {@link NoteBuilder#of(Timed[], TuningSystem)}
         */
        public static @NotNull FrequencyBuilder of(@NotNull TimedFrequency[] frequencies) {
            return new FrequencyBuilder(Arrays.asList(frequencies));
        }

        @Override
        public Voice get() {
            return build();
        }
    }
}

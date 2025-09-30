package com.team3316.orchestra.dsl;

import java.util.HashMap;
import java.util.Map;

import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.tuning.TuningSystem;
import com.team3316.orchestra.tuning.temperament.Equal24;
import com.team3316.orchestra.tuning.temperament.Equal31;
import com.team3316.orchestra.tuning.temperament.Kirnberger3;
import com.team3316.orchestra.tuning.temperament.Werckmeister3;
import com.team3316.orchestra.tuning.temperament.Werckmeister4;
import com.team3316.orchestra.tuning.temperament.Werckmeister5;
import com.team3316.orchestra.tuning.temperament.Werckmeister6;

/**
 * Tuning system aliases for the DSL
 */
public class TuningRegistry {
    private static final Map<String, TuningConstructor> registry = new HashMap<>();

    static {
        register(Equal24.class.getSimpleName(), Equal24::new);
        register(Equal31.class.getSimpleName(), Equal31::new);
        register(Kirnberger3.class.getSimpleName(), Kirnberger3::new);
        register(Werckmeister3.class.getSimpleName(), Werckmeister3::new);
        register(Werckmeister4.class.getSimpleName(), Werckmeister4::new);
        register(Werckmeister5.class.getSimpleName(), Werckmeister5::new);
        register(Werckmeister6.class.getSimpleName(), Werckmeister6::new);
    }

    /**
     * Register a new tuning system.
     * @param name Alias for the tuning system
     * @param construct Function that creates a tuning system
     * @return As {@link Map#putIfAbsent(Object, Object)}
     */
    public static TuningConstructor register(String name, TuningConstructor construct) {
        return registry.putIfAbsent(name, construct);
    }

    /**
     * Look up a tuning system in the registry.
     * @param name Alias
     * @return Tuning system if it exists, {@code null} otherwise.
     */
    public static TuningConstructor lookup(String name) {
        return registry.get(name);
    }

    /**
     * A function that can create a tuning system.
     */
    @FunctionalInterface
    public static interface TuningConstructor {
        /**
         * Create a tuning system using a reference note and reference pitch.
         * @param referenceNote Reference note
         * @param referencePitch Reference pitch
         * @return New tuning system
         */
        TuningSystem construct(NamedNote referenceNote, Pitch referencePitch);
    }
}

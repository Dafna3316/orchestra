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

    public static TuningConstructor register(String name, TuningConstructor c) {
        return registry.putIfAbsent(name, c);
    }

    public static TuningConstructor lookup(String name) {
        return registry.get(name);
    }

    @FunctionalInterface
    public static interface TuningConstructor {
        TuningSystem construct(NamedNote referenceNote, Pitch referencePitch);
    }
}

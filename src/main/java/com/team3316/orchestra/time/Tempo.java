package com.team3316.orchestra.time;

import java.io.Serializable;

import org.apache.commons.numbers.fraction.Fraction;

import edu.wpi.first.units.FrequencyUnit;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Frequency;
import edu.wpi.first.units.measure.Time;

/**
 * Musical tempo direction.
 * <p>
 * For example, ♩ = 120 would be {@code new Tempo(Fraction.of(1, 4), Tempo.BPM.of(120))}.
 * @param duration Note value parameter of the tempo
 * @param frequency Frequency
 */
public record Tempo(Fraction duration, Fraction frequency) implements Serializable {
    /**
     * Beats per minute unit.
     */
    public static final FrequencyUnit BPM = Units.Value.per(Units.Minute);

    public Tempo(Fraction duration, Frequency frequency) {
        this(duration, Fraction.from(frequency.in(Units.Hertz)));
    }

    /**
     * Realize a note value in this tempo.
     * @param duration Input value
     * @return Actual duration
     */
    public Time interpret(Fraction duration) {
        return Units.Value.of(duration.divide(duration()).doubleValue()).times(Units.Hertz.of(frequency.doubleValue()).asPeriod());
    }
}

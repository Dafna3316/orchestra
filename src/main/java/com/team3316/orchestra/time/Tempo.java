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
 * @param frequency Frequency (Hertz)
 */
public record Tempo(Fraction duration, Fraction frequency) implements Serializable {
    /**
     * Beats per minute unit.
     */
    public static final FrequencyUnit BPM = Units.Value.per(Units.Minute);

    /**
     * Construct a tempo.
     * @param duration Note value parameter
     * @param frequency WPILIB frequency
     */
    public Tempo(Fraction duration, Frequency frequency) {
        this(duration, Fraction.from(frequency.in(Units.Hertz)));
    }

    /**
     * Realize a note value in this tempo.
     * @param target Input value
     * @return Actual duration
     */
    public Time interpret(Fraction target) {
        return Units.Value.of(target.divide(duration).doubleValue()).times(Units.Hertz.of(frequency.doubleValue()).asPeriod());
    }
}

package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

import edu.wpi.first.units.FrequencyUnit;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Frequency;

/**
 * Musical tempo direction.
 * <p>
 * For example, ♩ = 120 would be {@code new Tempo(Fraction.of(1, 4), Tempo.BPM.of(120))}.
 * @param duration Note value parameter of the tempo
 * @param frequency Frequency
 */
public record Tempo(Fraction duration, Frequency frequency) {
    /**
     * Beats per minute unit.
     */
    public static final FrequencyUnit BPM = Units.Value.per(Units.Minute);
}

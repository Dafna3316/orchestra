package com.team3316.orchestra.pitch;

import java.util.function.DoubleSupplier;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Overengineered abstraction over a floating-point frequency.
 * <p>
 * The frequency is stored in three parts:
 * <ul>
 * <li>Base frequency (positive floating-point)</li>
 * <li>Just-intonation modifier (positive rational)</li>
 * <li>Semitones (rational)</li>
 * </ul>
 * The reason for this is simple-- I don't trust floating-point arithmetic.
 * 
 * @param baseFrequency The base from which the actual frequency is calculated (positive)
 * @param ratio A pure just-intonation ratio to apply to the base frequency (positive)
 * @param semitones Amount of semitones to add to the base frequency
 * 
 * @author Jonathan Dafna
 */
public record Pitch(double baseFrequency, @NotNull Fraction ratio, @NotNull Fraction semitones) implements DoubleSupplier {
    private static final double DEFAULT_FRQUENCY = 440;
    private static final Fraction DEFAULT_RATIO = Fraction.ONE;
    private static final Fraction DEFAULT_SEMITONES = Fraction.ZERO;

    /**
     * Calculate the real frequency to be heard.
     * @return Effective frequency of this pitch
     */
    public double frequency() {
        return baseFrequency * ratio.doubleValue() * Math.pow(2, semitones.divide(Fraction.of(12)).doubleValue());
    }

    @Override
    public double getAsDouble() {
        return frequency();
    }

    /**
     * Multiply the base frequency by a floating-point factor, with the loss
     * of precision entailed.
     * 
     * @param factor Factor to multiply by
     * @return New pitch with a new base frequency
     */
    @NotNull
    @Contract(pure = true)
    public Pitch rawMultiply(final double factor) {
        return new Pitch(baseFrequency * factor, ratio, semitones);
    }

    /**
     * Override the {@link #baseFrequency base frequency}.
     * 
     * @param baseFrequency New base frequency
     * @return New pitch with a new base frequency
     */
    @NotNull
    @Contract(pure = true)
    public Pitch withBaseFrequency(final double baseFrequency) {
        return new Pitch(baseFrequency, ratio, semitones);
    }

    /**
     * Override the {@link #ratio just-intonation ratio}.
     * 
     * @param ratio New ratio
     * @return New pitch with a new ratio
     */
    @NotNull
    @Contract(pure = true)
    public Pitch withRatio(final @NotNull Fraction ratio) {
        return new Pitch(baseFrequency, ratio, semitones);
    }

    /**
     * Override the {@link #semitones amount of semitones}.
     * 
     * @param semitones New amount of semitones
     * @return New pitch with a new amount of semitones
     */
    @NotNull
    @Contract(pure = true)
    public Pitch withSemis(final @NotNull Fraction semitones) {
        return new Pitch(baseFrequency, ratio, semitones);
    }

    /**
     * Multiply ("go up") the existing {@link #ratio just-intonation ratio} by the given ratio.
     * 
     * @param ratio Multiplier
     * @return New pitch with the product of ratios
     */
    @NotNull
    @Contract(pure = true)
    public Pitch upRatio(final @NotNull Fraction ratio) {
        return new Pitch(baseFrequency, this.ratio.multiply(ratio), semitones);
    }

    /**
     * Divide ("go down") the existing {@link #ratio just-intonation ratio} by the given ratio.
     * 
     * @param ratio Divisor
     * @return New pitch with the quotient of ratios
     */
    @NotNull
    @Contract(pure = true)
    public Pitch downRatio(final @NotNull Fraction ratio) {
        return upRatio(ratio.reciprocal());
    }

    /**
     * Multiply ("go up") the existing {@link #ratio just-intonation ratio} by the given integer.
     * 
     * @param ratio Multiplier
     * @return New pitch with the product of the ratio and the factor
     */
    @NotNull
    @Contract(pure = true)
    public Pitch upRatio(final int ratio) {
        return upRatio(Fraction.of(ratio));
    }

    /**
     * Divide ("go down") the existing {@link #ratio just-intonation ratio} by the given integer.
     * 
     * @param ratio Divisor
     * @return New pitch with the quotient of the ratio and the factor
     */
    @NotNull
    @Contract(pure = true)
    public Pitch downRatio(final int ratio) {
        return downRatio(Fraction.of(ratio));
    }

    /**
     * Multiply ("go up") the existing {@link #ratio just-intonation ratio} by the given ratio.
     * 
     * @param num Numerator
     * @param den Denominator
     * @return New pitch with the product of ratios
     */
    @NotNull
    @Contract(pure = true)
    public Pitch upRatio(final int num, final int den) {
        return upRatio(Fraction.of(num, den));
    }

    /**
     * Divide ("go down") the existing {@link #ratio just-intonation ratio} by the given ratio.
     * 
     * @param num Numerator
     * @param den Denominator
     * @return New pitch with the quotient of ratios
     */
    @NotNull
    @Contract(pure = true)
    public Pitch downRatio(final int num, final int den) {
        return downRatio(Fraction.of(num, den));
    }

    /**
     * Add the given number of semitones.
     * 
     * @param semitones Number of semitones to add
     * @return New pitch with additional semitones
     */
    @NotNull
    @Contract(pure = true)
    public Pitch upSemis(@NotNull final Fraction semitones) {
        return new Pitch(baseFrequency, ratio, this.semitones.add(semitones));
    }

    /**
     * Subtract the given number of semitones.
     * 
     * @param semitones Number of semitones to subtract
     * @return New pitch with additional semitones
     */
    @NotNull
    @Contract(pure = true)
    public Pitch downSemis(@NotNull final Fraction semitones) {
        return upSemis(semitones.negate());
    }

    /**
     * Add the given number of semitones.
     * 
     * @param semitones Number of semitones to add
     * @return New pitch with additional semitones
     */
    @NotNull
    @Contract(pure = true)
    public Pitch upSemis(final int semitones) {
        return upSemis(Fraction.of(semitones));
    }

    /**
     * Subtract the given number of semitones.
     * 
     * @param semitones Number of semitones to subtract
     * @return New pitch with additional semitones
     */
    @NotNull
    @Contract(pure = true)
    public Pitch downSemis(final int semitones) {
        return downSemis(Fraction.of(semitones));
    }

    public Pitch {
        if (baseFrequency <= 0) throw new IllegalArgumentException("Base frequency must be positive");
        if (ratio.compareTo(Fraction.ZERO) <= 0) throw new IllegalArgumentException("Just-intonation ratio must be positive");
    }

    /**
     * Default values.
     * <p>
     * This produces a pitch at A440.
     */
    public Pitch() {
        this(DEFAULT_FRQUENCY, DEFAULT_RATIO, DEFAULT_SEMITONES);
    }

    /**
     * Create a pitch.
     * 
     * @param baseFrequency {@see #baseFrequency}
     * @return New pitch corresponding to the frequency
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final double baseFrequency) {
        return new Pitch(baseFrequency, DEFAULT_RATIO, DEFAULT_SEMITONES);
    }

    /**
     * Create a pitch.
     * 
     * @param ratio {@see #ratio}
     * @return New pitch
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(@NotNull final Fraction ratio) {
        return new Pitch(DEFAULT_FRQUENCY, ratio, DEFAULT_SEMITONES);
    }

    /**
     * Create a pitch.
     * 
     * @param num Numerator of the {@link #ratio just-intonation ratio}
     * @param den Denominator of the {@link #ratio just-intonation ratio}
     * @return New pitch.
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final int num, final int den) {
        return Pitch.of(Fraction.of(num, den));
    }

    /**
     * Create a pitch.
     * 
     * @param semitones {@see #semitones}
     * @return New pitch.
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final int semitones) {
        return new Pitch(DEFAULT_FRQUENCY, DEFAULT_RATIO, Fraction.of(semitones));
    }

    /**
     * Create a pitch.
     * 
     * @param baseFrequency {@see #baseFrequency}
     * @param num Numerator of the {@see #ratio just-intonation ratio}
     * @param den Denominator of the {@see #ratio just-intonation ratio}
     * @param semis {@see #semitones}
     * @return New pitch with the given parameters.
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final double baseFrequency, final int num, final int den, final int semis) {
        return new Pitch(baseFrequency, Fraction.of(num, den), Fraction.of(semis));
    }

    /**
     * Create a pitch.
     * 
     * @param baseFrequency {@see #baseFrequency}
     * @param num Numerator of the {@see #ratio just-intonation ratio}
     * @param den Denominator of the {@see #ratio just-intonation ratio}
     * @param semis {@see #semitones}
     * @return New pitch with the given parameters.
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final double baseFrequency, final int num, final int den, @NotNull final Fraction semis) {
        return new Pitch(baseFrequency, Fraction.of(num, den), semis);
    }

    /**
     * Create a pitch.
     * 
     * @param baseFrequency {@see #baseFrequency}
     * @param ratio {@see #ratio}
     * @param semitones {@see #semitones}
     * @return New pitch with the given parameters.
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final double baseFrequency, final int ratio, @NotNull final Fraction semitones) {
        return new Pitch(baseFrequency, Fraction.of(ratio), semitones);
    }

    /**
     * Create a pitch.
     * 
     * @param baseFrequency {@see #baseFrequency}
     * @param ratio {@see #ratio}
     * @param num Numerator of the {@link #semitones number of semitones}
     * @param den Denominator of the {@link #semitones number of semitones}
     * @return New pitch with the given parameters.
     */
    @NotNull
    @Contract(pure = true)
    public static Pitch of(final double baseFrequency, @NotNull final Fraction ratio, final int num, final int den) {
        return new Pitch(baseFrequency, ratio, Fraction.of(num, den));
    }
}

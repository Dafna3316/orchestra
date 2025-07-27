package com.team3316.orchestra.time;

import org.apache.commons.numbers.fraction.Fraction;

import edu.wpi.first.units.measure.Frequency;

public record Tempo(Fraction duration, Frequency frequency) {}

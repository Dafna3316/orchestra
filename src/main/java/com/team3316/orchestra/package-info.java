/**
 * An alternative abstraction over the TalonFX music API with finer control
 * over frequency and tuning than CTRE Orchestra.
 *
 * <h2>Low-level frequency pitch API</h2>
 * For the simplest of uses, {@link Pitch} should
 * be sufficient. It allows tuning by semitones as well as just ratios, but
 * doesn't support note names and intervals.
 *
 * <h2>High-level tuning system API</h2>
 * To get note names, one must choose a {@link TuningSystem}. {@link NamedNote}
 * already has support for intervals, so melodies can be created by a pipeline
 * of your {@link NamedNote} into your {@link TuningSystem}.
 *
 * <p>
 * To transpose pitches by intervals after they've been tuned, a
 * {@link ContextlessIntervalInterpreter} is necessary.
 * These concepts are orthogonal; some systems implement both, and some only
 * one.
 *
 * <p>
 * The following are both a {@link TuningSystem} and a {@link ContextlessIntervalInterpreter}:
 * <ul>
 * <li>12TET/24TET, implemented by {@link com.team3316.orchestra.tuning.temperament.TwelveTET}</li>
 * <li>31TET</li>
 * </ul>
 *
 * <p>
 * The following are only a {@link TuningSystem}:
 * <ul>
 * <li>¼-comma meantone</li>
 * <li>Kirnberger III, implemented by {@link com.team3316.orchestra.tuning.temperament.Kirnberger3}</li>
 * </ul>
 *
 * <p>
 * The following are only a {@link ContextlessIntervalInterpreter}:
 * <ul>
 * <li>Just intonation</li>
 * </ul>
 *
 * <h2>Loading MIDI files</h2>
 * TBD
 */
package com.team3316.orchestra;

import com.team3316.orchestra.pitch.Pitch;
import com.team3316.orchestra.pitch.NamedNote;
import com.team3316.orchestra.tuning.TuningSystem;
import com.team3316.orchestra.tuning.ContextlessIntervalInterpreter;

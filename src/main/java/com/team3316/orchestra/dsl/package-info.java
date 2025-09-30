/**
 * DBugOrchestra DSL
 * <p>
 * This is a loose subset of <a href="https://lilypond.org">Lilypond</a> syntax.
 *
 * <h2>Syntax and Semantics</h2>
 * Each DSL file corresponds exactly to a {@link com.team3316.orchestra.Piece} object.
 * Such a piece consists of some initial declarations and a list of voices (each of which
 * corresponds to a {@link com.team3316.orchestra.voice.VoiceSupplier}).
 * Voices are separated by {@code \\}.
 * 
 * <h3>Comments</h3>
 * Line comments begin with {@code %%}, and block comments begin with <code>%{</code>
 * and end with <code>%}</code>.
 *
 * <h3>Initial Declarations</h3>
 * At the very beginning of the file, there can be found three kinds of declarations
 * (in any order). Any or all of them have default alternatives and can be omitted,
 * but preferably all three should be present.
 * Each declaration can be repeated, but only the last value takes effect.
 * <p>
 * The {@code \storage} declaration specifies how the notes or pitches of the resulting
 * piece should be stored (in memory, and later in serialized binary form).
 * It accepts one argument, of three possible values:
 * <ul>
 * <li>{@code note}, which stores notes as {@link com.team3316.orchestra.pitch.NamedNote}
 * objects, and thus requires the selected tuning system to be available at play-time.
 * <li>{@code pitch}, which stores notes as {@link com.team3316.orchestra.pitch.Pitch}
 * objects. Since note names aren't saved, later transposition (other than by a {@link com.team3316.orchestra.tuning.ContextlessIntervalInterpreter})
 * is impossible, but the selected tuning system need not be available at play-time.
 * <li>{@code frequency}, which stores notes as {@code double}s, representing a frequency
 * in Hertz. This is more space-efficient, and direct frequency transposition is possible,
 * but with much higher risk of precision loss.
 * </ul>
 * The default is {@code note}.
 * <p>
 * The {@code \tuning} declaration specifies how note names should be tuned. It's thus
 * irrelevant when entering pitches only (see below).
 * It accepts three arguments; the first identifies the {@link com.team3316.orchestra.tuning.TuningSystem}
 * constructor, and the other two are used as a reference note name and pitch to construct it.
 * <p>
 * The first argument must be a string literal, and refer either to an existing alias
 * in {@link TuningRegistry}, or a Java fully-qualified class name (as accepted by {@link Class#forName(String)})
 * which has constructor with signature
 * {@code <init>}({@link com.team3316.orchestra.pitch.NamedNote}, {@link com.team3316.orchestra.pitch.Pitch}).
 * The first argument is thought to be a reference note (useful for non-equal temperaments), and
 * the second a pitch which the note maps to. Refer to {@link TuningRegistry#register(String, com.team3316.orchestra.dsl.TuningRegistry.TuningConstructor) TuningRegistry.register}
 * to add your own aliases.
 * <p>
 * The second argument is in the <i>note-name</i> syntax, and the third in the <i>pitch</i> syntax,
 * both described in more detail below.
 * <p>
 * The {@code \tempo} declaration sets the tempo for the entire piece, and accepts two
 * arguments, separated by {@code =}; the first one being a duration, and the second
 * an integer or floating-point number corresponding to the {@link com.team3316.orchestra.time.Tempo#BPM BPM}.
 * The <i>duration</i> syntax is described in more detail below.
 *
 * <h3>Note-Name Syntax</h3>
 * Note names are specified by an identifier (corresponding to {@link com.team3316.orchestra.pitch.Diatonic}
 * and {@link com.team3316.orchestra.pitch.Accidental}) and octave marks.
 * <p>
 * Like Lilypond's default behaviour, we accept <b>Dutch note names</b>. The basic diatonic
 * naturals are named {@code c}, {@code d}, {@code e}, {@code f}, {@code g}, {@code a}, and {@code b}
 * (the Dutch system, unlike German, doesn't use H). The eight possible accidentals are described by
 * suffixes after the basic diatonic note:
 * <ul>
 * <li>{@code is} for {@link com.team3316.orchestra.pitch.Accidental#SHARP sharp}
 * <li>{@code es} for {@link com.team3316.orchestra.pitch.Accidental#FLAT flat}
 * <li>{@code isis} for {@link com.team3316.orchestra.pitch.Accidental#DOUBLE_SHARP double-sharp}
 * <li>{@code eses} for {@link com.team3316.orchestra.pitch.Accidental#DOUBLE_FLAT double-flat}
 * <li>{@code ih} for {@link com.team3316.orchestra.pitch.Accidental#HALF_SHARP half-sharp}
 * <li>{@code eh} for {@link com.team3316.orchestra.pitch.Accidental#HALF_FLAT half-flat}
 * <li>{@code ihih} for {@link com.team3316.orchestra.pitch.Accidental#SESQUISHARP sesquisharp}
 * <li>{@code eheh} for {@link com.team3316.orchestra.pitch.Accidental#SESQUIFLAT sesquiflat}
 * </ul>
 * There are also contractions: {@code ees} -> {@code es}, {@code aes} -> {@code as}, and
 * similarly for other accidentals beginning with 'e'.
 * <p>
 * Octaves are given in a variant of <a href="https://en.wikipedia.org/wiki/Helmholtz_pitch_notation">Helmholtz pitch notation</a>;
 * no additional marks specifies the small octave (the octave from C–B below middle C),
 * a sequence of ticks ({@code '}) specifies the first, second, third octave etc.,
 * and a sequence of commas ({@code ,}) specifies the great, contra, sub-contra octave etc.
 * Note that this is one comma more than Helmholtz pitch notation, as we don't allow
 * capital-letter note names.
 *
 * <h3>Pitch Syntax</h3>
 * Pitches, as in {@link com.team3316.orchestra.pitch.Pitch}, consist of a base frequency,
 * a pure rational multiple, and an amount of additional semitones.
 * <p>
 * The base frequency is simply given as an integer or floating-point number. It may be
 * optionally followed by a sequence of terms of one of the forms:
 * <ul>
 * <li>{@code *r}, where {@code r} is a rational number (integer like {@code n} or {@code n/n}),
 * to multiply by the given rational
 * <li>{@code +r}, where {@code r} is a rational, to add the given amount of semitones
 * <li>{@code -r}, where {@code r} is a rational, to subtract the given amount of semitones
 * </ul>
 * Each of these can be given multiple times in any order, and their effect accumulates.
 *
 * <h3>Duration Syntax</h3>
 * Durations are defined inductive as either:
 * <ul>
 * <li>An integer, corresponding to the reciprocal of that integer
 * <li>An integer with a sequence of augmentation dots ({@code .}), multiplying by 1½ for each dot
 * <li>A sum of two other durations
 * <li>Another duration {@code *} a rational
 * </ul>
 *
 * <h3>Music Expression</h3>
 * Each voice consists of one <i>music expression</i>, and voices are separated by {@code \\}.
 * There are three kinds of music expressions:
 * <ul>
 * <li>Musical <i>atoms</i> (named-note, timed pitch and rest): these represent a single note and a duration.
 * <li>Sequential music: this is simply a list of whitespace-separated music expressions enclosed in curly braces {@code {}}.
 * <li>Fixed music: sequential music preceded by {@code \fixed} and optionally a note name.
 * <li>Relative music: sequential music precded by {@code \relative} and optionally a note name.
 * </ul>
 *
 * <h4>Atoms</h4>
 * A named note and a timed pitch are simply timed versions of the note-name and the pitch.
 * The named note is parsed as a note-name directly followed by a duration (no
 * separator needed), while a timed pitch is parsed as a pitch and a duration,
 * separated by a colon ({@code :}).
 * <p>
 * Rests are given just like named notes by using {@code r} in place of the note-name.
 * <p>
 * Named notes given in this way might be transposed in a fixed or relative context (see below).
 * <p>
 * The duration can be omitted (but a pitch must keep the {@code :}), to use the duration
 * of the previous atom, which is even preserved across disjoint expressions and voices.
 * <p>
 * Atoms are added in the order of encounter to the current voice. <b>Note</b>: a timed-pitch
 * atom forces the current voice to switch from {@code note} to {@code pitch}-type storage
 * <b>for the entire voice</b>, regardless of the {@code \storage} declaration. A warning is
 * printed when this is done. {@code frequency} storage is unaffected.
 *
 * <h4>Sequential Music</h4>
 * Sequential music doesn't change the fixed or relative context, and only serves as
 * a convenient visual grouping.
 *
 * <h4>Fixed Music</h4>
 * A fixed expression causes note names inside to be transposed by a constant amount
 * of octaves, depending on the given note-name (or reset the transposition to octave 0
 * if none is supplied). Only the octave marks matter, not the name or the accidental!
 * Raw pitches remain unaffected.
 * <p>
 * Fixed and relative expressions only affect the notes within them (other than relative
 * state changes), and cancel the effect of their parents.
 *
 * <h4>Relative Music</h4>
 * This is the most complicated kind of expression, and the most useful for entering
 * melodies. In relative mode, note names (pitches are unaffected) are initially
 * interpreted as the closest variant to the previous note name (i.e. within a fourth),
 * with octave marks transposing. In other words, relative mode changes the meaning of
 * "octave 0" to align with a fourth-sized neighbourhood around the previous note.
 * <p>
 * If a note-name is given after {@code \relative}, the first note name in the block
 * is relative to the given note. Otherwise, the first note is interpreted normally,
 * as if it were outside relative mode.
 * <p>
 * Fixed and relative expressions only affect the notes within them (other than relative
 * state changes), and cancel the effect of their parents.
 *
 * <h2>Example</h2>
 * <pre>
 * \tuning "Equal31" a' 440
 * \storage note
 * \tempo 4 = 124
 * \relative c' {
 *     c4 d e c |
 *     c d e c |
 *     e f g2 |
 *     e4 f g2 |
 *     g8 a g f e4 c |
 *     g'8 a g f e4 c |
 *     c g c2 |
 *     c4 g c2
 *     %% Line comment
 *     %{
 *     block
 *     comment
 *     %}
 * }
 * \\
 * {
 *     e'8 e'16 b e'8 fis' gis'8 gis'16 e' fis'8 a' |
 *     gis'4 b' gis' b'
 * }
 * \\
 * \relative c' {
 *     c4+8 d8 e4. c8 |
 *     e4 c e4 r |
 *     d4*3/2 e8 f f e d |
 *     f1 |
 *     e4. f8 g4. e8 |
 *     g4 e g2 |
 *     f4. g8 a a g f |
 *     a1
 * }
 * </pre>
 *
 * <h2>Tuning Registry</h2>
 */
package com.team3316.orchestra.dsl;

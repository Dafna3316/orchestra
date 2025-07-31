package com.team3316.orchestra.voice;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * Allowed types in a {@link com.team3316.orchestra.Piece}.
 */
public sealed interface VoiceSupplier extends Supplier<Voice>, Serializable permits Voice, Voice.PitchBuilder, Voice.NoteBuilder, Voice.FrequencyBuilder {}

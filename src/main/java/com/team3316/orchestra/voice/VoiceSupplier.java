package com.team3316.orchestra.voice;

import java.io.Serializable;
import java.util.function.Supplier;

public sealed interface VoiceSupplier extends Supplier<Voice>, Serializable permits Voice, Voice.Builder, Voice.NoteBuilder, Voice.FrequencyBuilder {}

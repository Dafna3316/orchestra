package com.team3316.orchestra;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * A {@link Subsystem} that provides a set of players.
 */
public interface Orchestra extends Subsystem {
    /**
     * Return the players.
     * @return Players in this orchestra
     */
    TalonFX[] getPlayers();

    /**
     * Make a {@link Command} to play a piece, and evaluate everything at the call site.
     * @param piece Piece to play
     * @return Command that plays {@code piece}
     */
    default Command playEagerly(Piece piece) {
        return piece.playEagerly(this);
    }

    /**
     * Make a {@link Command} to play a piece, and compute as needed.
     * @param piece Piece to play
     * @return Command that plays {@code piece}
     */
    default Command playLazily(Piece piece) {
        return piece.playLazily(this);
    }
}

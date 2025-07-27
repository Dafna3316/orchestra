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

    default Command playEagerly(Piece piece) {
        return piece.playEagerly(this);
    }

    default Command playLazily(Piece piece) {
        return piece.playLazily(this);
    }
}

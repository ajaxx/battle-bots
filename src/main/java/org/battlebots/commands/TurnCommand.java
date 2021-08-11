package org.battlebots.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.battlebots.objects.Atom;

/**
 * Request to issue a vector turn.
 */

public class TurnCommand implements Command {
    @JsonProperty("angle")
    private double turnAngle;

    public double getTurnAngle() {
        return turnAngle;
    }

    public void setTurnAngle(double turnAngle) {
        this.turnAngle = turnAngle;
    }

    /**
     * Applies a thrust command.
     * @param atom
     */
    public void apply(final Atom atom) {
        // TBD: place a floor and ceiling on turnAngle
        atom.applyTurn(turnAngle);
    }
}

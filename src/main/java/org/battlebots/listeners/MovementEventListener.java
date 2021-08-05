package org.battlebots.listeners;

import org.battlebots.events.MovementEvent;

public interface MovementEventListener {
    /**
     * Occurs when a movement event occurs.
     * @param movementEvent the details of the movement.
     */
    public void onMovementEvent(MovementEvent movementEvent);
}

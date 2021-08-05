package org.battlebots.spring.controllers;

import org.battlebots.arena.Arena;
import org.battlebots.arena.Simulator;
import org.battlebots.arena.SimulatorFactory;
import org.battlebots.events.MovementEvent;
import org.battlebots.messages.InitialStateMessage;
import org.battlebots.messages.ListAtomsRequest;
import org.battlebots.messages.ListAtomsResponse;
import org.battlebots.messages.StartStopMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class SimulationController {
    private final SimpMessagingTemplate webSocket;
    private SimulatorFactory simulatorFactory;
    private Simulator simulation;

    public SimulationController(final SimulatorFactory simulationFactory,
                                final SimpMessagingTemplate webSocket) {
        this.simulatorFactory = simulationFactory;
        this.simulation = simulationFactory.createSimulator();
        this.webSocket = webSocket;
    }

    @PostConstruct
    private void initialize() {
        this.simulation.getArena().addMovementListener(this::publishMovementEvent);
    }

    /**
     * Publishes a movement event to listeners.
     * @param movementEvent
     */
    private void publishMovementEvent(final MovementEvent movementEvent) {
        webSocket.convertAndSend("/arena.movement", movementEvent);

    }

    public Simulator getSimulation() {
        return simulation;
    }

    public Arena getArena() {
        return getSimulation().getArena();
    }

    @SubscribeMapping("/arena.login")
    public InitialStateMessage onClientLogin(SimpMessageHeaderAccessor accessor) {
        // send messages to the client
        final Arena arena = getArena();
        final InitialStateMessage arenaStateMessage = new InitialStateMessage(
                arena.getWidth(),
                arena.getHeight(),
                arena.getAtoms());
        return arenaStateMessage;
    }

    @MessageMapping("/atoms")
    public ListAtomsResponse onGetAtoms(final ListAtomsRequest request) {
        return new ListAtomsResponse(getArena().getAtoms());
    }

    @MessageMapping("/start-stop")
    @SendTo("/topic/arena")
    public void onStartStop(final StartStopMessage startStopMessage) {
        if (simulation.isRunning()) {
            simulation.stop();
        } else {
            simulation.start();
        }
    }
}

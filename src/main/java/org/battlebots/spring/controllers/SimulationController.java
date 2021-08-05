package org.battlebots.spring.controllers;

import org.battlebots.arena.Arena;
import org.battlebots.arena.Simulator;
import org.battlebots.arena.SimulatorFactory;
import org.battlebots.messages.InitialStateMessage;
import org.battlebots.messages.ListAtomsRequest;
import org.battlebots.messages.ListAtomsResponse;
import org.battlebots.messages.StartStopMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SimulationController {
    private SimulatorFactory simulatorFactory;
    private Simulator simulation;

    public SimulationController(final SimulatorFactory simulationFactory) {
        this.simulatorFactory = simulationFactory;
        this.simulation = simulationFactory.createSimulator();
    }

    public Simulator getSimulation() {
        return simulation;
    }

    public Arena getArena() {
        return getSimulation().getArena();
    }

    @SubscribeMapping("/topic/arena")
    public InitialStateMessage onClientStartup() {
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

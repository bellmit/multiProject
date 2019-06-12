package service;

import org.junit.Test;
import util.SimulationHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimulationTest {

    @Test
    public void testStartAndReceiveSimulation() throws TimeoutException {
        SimulationHandler simulationHandler = new SimulationHandler();
        try {
            String coords = "(5.482373,51.438115),(5.483191,51.438245)";
            simulationHandler.sendCoords(coords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

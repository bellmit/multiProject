package socket;


import event.SimulationEvent;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/simulation/{orderid}/websocket",
        encoders = SimulationEncoder.class,
        decoders = SimulationDecoder.class,
        configurator =  ServerConfig.class)
public class SimulationSocket {

    private static final Map<String, Set<Session>> sessions = Collections.synchronizedMap(new HashMap<>());

    public SimulationSocket() {
        //we need an empty constructor to gain access
    }

    @OnOpen
    public void onOpen(@PathParam("orderid") String orderid, Session session) throws IOException, EncodeException {
        if(orderid!=null){
            if(!sessions.containsKey(orderid)){
                Set<Session> set = new HashSet<>();
                set.add(session);
                sessions.put(orderid,set);
            }else{
                Set<Session> set = sessions.get(orderid);
                set.add(session);
                sessions.put(orderid,set);
            }
            List<String> orderids = new ArrayList<>();
            orderids.add(orderid);
            SimulationEvent simulationEvent = new SimulationEvent("5.478337","51.436736",orderids,"restaurant");
            session.getBasicRemote().sendObject(simulationEvent);
        }
    }

    @OnClose
    public void onClose(@PathParam("orderid") String orderid, Session session){
        if(orderid!=null){
            for(Session ses : sessions.get(orderid)){
                if(ses.getId().equals(session.getId())){
                    sessions.get(orderid).remove(ses);
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message,Session session){
        //we dont need this for this instance
    }

    public void sendUpdateSimulation(SimulationEvent simulationEvent) {
        if(sessions.containsKey("manager")){
            for(Session session : sessions.get("manager")){
                try {
                    session.getBasicRemote().sendObject(simulationEvent);
                } catch (IOException | EncodeException ex) {
                    Logger.getLogger(SimulationSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (String id : simulationEvent.getOrderid()) {
            if (sessions.containsKey(id)) {
                for (Session session : sessions.get(id)) {
                    try {
                        session.getBasicRemote().sendObject(simulationEvent);
                    } catch (IOException | EncodeException ex) {
                        Logger.getLogger(SimulationSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

}

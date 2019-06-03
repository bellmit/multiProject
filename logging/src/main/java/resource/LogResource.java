package resource;

import domain.Component;
import service.LogService;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("logs")
public class LogResource {

    private LogService logService;

    @Inject
    public void setLogService(LogService logService){
        this.logService = logService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok(logService.getAllLogs()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByComponent(@HeaderParam("component") String component) {
        return Response.ok(logService.getLogsForComponent(Component.valueOf(component))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLevel(@HeaderParam("level") String level) {
        return Response.ok(logService.getLogsWithLevel(Level.parse(level))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByComponentWithLevel(@HeaderParam("component") String component,
                                            @HeaderParam("level") String level) {
        return Response.ok(logService.getLogsForComponentWithLevel(Component.valueOf(component), Level.parse(level))).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTestLog(@HeaderParam("message") String message) {
        return Response.ok(logService.addTestLog(message)).build();
    }
}

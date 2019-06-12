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
    public Response get(@QueryParam("component") String component,
                        @QueryParam("level") String level) {
        if(component != null && level != null){
            return Response.ok(logService.getLogsForComponentWithLevel(Component.valueOf(component), Level.parse(level))).build();
        } else if (level != null) {
            return Response.ok(logService.getLogsWithLevel(Level.parse(level))).build();
        } else if (component != null) {
            return Response.ok(logService.getLogsForComponent(Component.valueOf(component))).build();
        } else {
            return Response.ok(logService.getAllLogs()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTestLog(@HeaderParam("message") String message,
                               @HeaderParam("level") String level) {
        if(message !=null && level !=null){
            return Response.ok(logService.addTestLog(message, level)).build();
        }else if(message != null){
            return Response.ok(logService.addTestLog(message)).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

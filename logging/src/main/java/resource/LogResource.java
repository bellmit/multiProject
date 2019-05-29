package resource;

import service.LogService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

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
}

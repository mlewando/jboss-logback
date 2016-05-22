package hello;

import hello.ejb.Motorcycle;
import hello.ejb.TestBean;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/rest")
@ManagedBean
public class RestEndpoint {

    @EJB
    private TestBean ejbBean;

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Motorcycle motorcycle) {
        log.info("will save moto: {}", motorcycle);
        motorcycle = ejbBean.saveMotorcycle(motorcycle);
        return Response.status(Response.Status.OK).entity(motorcycle).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        System.out.println("test before receive moto");
        log.info("retrieve moto with id {}", id);
        Motorcycle motorcycle = ejbBean.getMotorcycle(id);
        if (motorcycle != null) {
            return Response.status(Response.Status.OK).entity(motorcycle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}

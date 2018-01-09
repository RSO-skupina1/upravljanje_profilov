package rest;

import bean.ProfilBean;
import config.ConfigurationData;
import pojo.Profil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("upravljanje_profilov")
public class UpravljanjeProfilov {
    @Inject
    private ProfilBean profilBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Path("/test")
    public Response test() {
        return Response.ok("OK").build();
    }

    @Inject
    private ConfigurationData configurationData;

    @GET
    @Path("/{profilId}")
    public Response getProfil(@PathParam("profilId") String profilId) {

        Profil profil = profilBean.getProfil(profilId);

        if (profil == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(profil).build();
    }

    @PUT
    @Path("{profilId}")
    public Response updateProfil(@PathParam("profilId") String profilId, Profil profil) {

        profil = profilBean.getProfil(profilId);

        if (profil == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (profil.getId() != null)
                return Response.status(Response.Status.OK).entity(profil).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }
}

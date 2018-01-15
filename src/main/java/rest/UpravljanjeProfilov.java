package rest;

import bean.ProfilBean;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import config.ConfigurationData;
import pojo.Profil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("upravljanje_profilov")
public class UpravljanjeProfilov {

    @Inject
    @DiscoverService(value = "katalog_profilov-service", version = "*", environment = "dev")
    private WebTarget target;

    @Inject
    private ProfilBean profilBean;


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

       // WebTarget service = target.path("v1/katalog_profilov");

        if (profil == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(profil).build();
    }

    @PUT
    @Path("{profilId}")
    public Response updateProfil(@PathParam("profilId") String profilId) {

        WebTarget service = target.path("/v1/katalog_profilov");
        Profil profil = profilBean.getProfil(profilId);
        if (profil == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        profil.setIme("JANEZ"); //configurationData.getImeNovega()
        profil.setPriimek("Novak");
        System.out.println("ID:" + profil.getId());
        //return ClientBuilder.newClient().target(service.getUri())
        return ClientBuilder.newClient().target("http://192.168.99.100:8082/v1/katalog_profilov/" + profilId)
                .request().put(Entity.json(profil));
    }
}

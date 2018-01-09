package bean;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import pojo.Profil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Date;

@RequestScoped
public class ProfilBean {
    @Inject
    @DiscoverService(value = "katalog_profilov-service", environment = "dev", version = "*")
    private WebTarget target;

    @CircuitBreaker(requestVolumeThreshold = 2)
    @Fallback(fallbackMethod = "getProfilFallback")
    @Timeout
    public Profil getProfil(String profilId) {
        try {
            WebTarget service = target.path("v1/katalog_profilov");

            Response katalogProfilovResponse = ClientBuilder.newClient().target(service.getUri())
                    .path(profilId).request().get();

            return katalogProfilovResponse.readEntity(Profil.class);
        } catch (WebApplicationException e) {
            System.out.println(e);
        } catch (ProcessingException e) {
            System.out.println(e);
        }

        return null;
    }

    public Profil getProfilFallback(String profilId) {

        Profil p = new Profil();
        p.setIme("Janez");
        p.setPriimek("Novak");
        p.setId(profilId);

        return p;

    }
}

package config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-endpoints")
public class ConfigurationData {

    @ConfigValue(watch = true)
    private String profilId;

    public String getProfilId() {
        return profilId;
    }

    public void setProfilId(String profilId) {
        this.profilId = profilId;
    }

    @Override
    public String toString() {
        return "ConfigurationData{" +
                "profilId='" + profilId + '\'' +
                '}';
    }
}
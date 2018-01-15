package config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-endpoints")
public class ConfigurationData {

    @ConfigValue(watch = true)
    private String ime;

    public String getImeNovega() {
        return ime;
    }

    public void setImeNovega(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return "ConfigurationData{" +
                "imeNovega='" + ime + '\'' +
                '}';
    }
}
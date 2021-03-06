package ch.zuehlke.camp.ejb;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ch.zuehlke.camp.rest.ArtistResource;
import ch.zuehlke.camp.rest.CompanyResource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest-prefix")
public class ApplicationConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
        		SimpleRESTPojo.class, 
        		SimpleRESTEJB.class, 
        		HelloWorldResource.class, 
        		EchoBean.class,
        		Setup.class,
        		Check.class,
        		FindFood.class,
        		AddMeal.class,
        		Report.class,
        		ArtistResource.class,
        		CompanyResource.class));
    }
}
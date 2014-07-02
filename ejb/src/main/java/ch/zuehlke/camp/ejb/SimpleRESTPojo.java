package ch.zuehlke.camp.ejb;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.zuehlke.camp.ejb.cdi.Greeter;
import ch.zuehlke.camp.ejb.cdi.annotation.SpecialQualifier;

import java.util.Date;

@Path("/pojo")
public class SimpleRESTPojo {
	
	@Inject
	@SpecialQualifier
	private Greeter greeter;
	
    @GET
    public String pojo() {
        return "pojo ok @ " + new Date().toString() 
        		+ " " 
        		+ greeter.getGreeting("Java Developer")
        		+ " Greeter id: " + greeter.getId();
    }
}
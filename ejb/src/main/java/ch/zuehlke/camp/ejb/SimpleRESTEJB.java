package ch.zuehlke.camp.ejb;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Date;

@Singleton
@Lock(LockType.READ)
@Path("/ejb")
public class SimpleRESTEJB {
    @GET
    public String ejb() {
        return "ejb ok @ " + new Date().toString();
    }
}
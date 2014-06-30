package ch.zuehlke.camp.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Stateless
@Remote(EchoBeanRemote.class)
@Path("echo")
public class EchoBean implements EchoBeanRemote {

	@Override
	@GET
	@Path("{something}")
	public String echo(@PathParam("something")String echoText) {
		return "Echo " + echoText;
	}

}

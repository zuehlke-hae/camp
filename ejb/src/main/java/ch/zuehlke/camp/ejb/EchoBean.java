package ch.zuehlke.camp.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(EchoBeanRemote.class)
public class EchoBean implements EchoBeanRemote {

	@Override
	public String echo(String echoText) {
		return "Echo " + echoText;
	}

}

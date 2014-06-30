package ch.zuehlke.camp.ejb.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.zuehlke.camp.ejb.EchoBeanRemote;

public class Client {

	public static void main(String[] args) throws NamingException {
		Properties jndiProps = buildJndiProperties();

		Context ctx = new InitialContext(jndiProps);

		Object lookup = ctx
				.lookup("ejb-app/ejb/EchoBean!ch.zuehlke.camp.ejb.EchoBeanRemote");

		EchoBeanRemote bean = (EchoBeanRemote) lookup;

		System.out.println(bean.echo("Hello World"));
	}

	private static Properties buildJndiProperties() {
		Properties jndiProps = new Properties();

		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "client");
		jndiProps.put(Context.SECURITY_CREDENTIALS, "password");
		jndiProps.put("jboss.naming.client.ejb.context", true);

		return jndiProps;
	}

}

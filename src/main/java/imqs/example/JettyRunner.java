package imqs.example;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * A Jetty runner for the services provided by the servlets in this example. It
 * is useful as a debugging and test environment.
 *
 * @author David Weber
 *
 */
public class JettyRunner {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		context.setDescriptor("src/webapp/WEB-INF/web.xml");
		context.setResourceBase("src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);

		server.setHandler(context);

		server.start();
		server.join();
	}
}

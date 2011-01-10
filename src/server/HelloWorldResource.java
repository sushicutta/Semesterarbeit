package server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//The Java class will be hosted at the URI path "/helloworld"

@Path("/HelloWorld")
@Produces(MediaType.TEXT_HTML)
public class HelloWorldResource {

	/*
	 * Text HTML
	 */
	@GET
	public String produceDefault() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n");
		sb.append("  <head><title>blafasel</title></head>\n");
		sb.append("  <body>Das ist der Body</body>\n");
		sb.append("</html>");
		return sb.toString();
	}

	/*
	 * Text
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String produceTextPlain() {
		return "Hello World";
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void consumeTextPlain(String message) {
		// Store the message
	}

	/*
	 * Text XML
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String produceXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<action>\n");
		sb.append("  <message>Hello World</message>\n");
		sb.append("</action>");
		return sb.toString();
	}
}
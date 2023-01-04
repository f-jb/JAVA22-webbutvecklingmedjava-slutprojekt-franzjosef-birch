package apiManagement;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.tomcat.jakartaee.commons.io.IOUtils;

public class APIgetter {
	private String server;
	private String[] arguments;
	private String apikey;
	private URL url;

	
	public APIgetter(String server, String apikey, String... args ) throws MalformedURLException{
		this.server = server;
		this.apikey = apikey;
		this.arguments = args;
	}
	
	public String getData() throws IOException{
		finalizeURL();
		URLConnection apiconnection = url.openConnection();
		InputStream in = apiconnection.getInputStream();
		String encoding = apiconnection.getContentEncoding();
		encoding = encoding == null? "UTF-8": encoding;
		String body = IOUtils.toString(in,encoding);
		
		
		return body;
	}
	
	private void finalizeURL() throws MalformedURLException {
		String serverString = server + "?";
		for(String argument: arguments) {
			serverString += argument+"&";
		}
		serverString += apikey;
		
		this.url = new URL(serverString);
	}

}

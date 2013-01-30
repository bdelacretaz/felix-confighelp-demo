package ch.x42.felix.confighelp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

/** Example Servlet the provides overridable resources for the 
 *  Apache Felix webconsole.
 */
@Component(immediate=true)
@Service
@Property(name="org.apache.felix.resources.servlet", boolValue=true)
@SuppressWarnings("serial")
public class ConfigHelpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String resourcePath = req.getPathInfo();
        final InputStream src = getClass().getResourceAsStream(resourcePath);
        if(src == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "resource not found: " + resourcePath);
        }
        
        // TODO should use mime type service... 
        resp.setContentType("application/javascript");
        
        try {
            final OutputStream os = resp.getOutputStream();
            final byte[] buffer = new byte[4096];
            int len = 0;
            while( (len = src.read(buffer, 0, buffer.length)) > 0) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } finally {
            src.close();
        }
    }
}
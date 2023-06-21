
package com.github.ulwx.aka.admin.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class CompressionServletResponseWrapper extends HttpServletResponseWrapper {

	private static final Logger log = LoggerFactory.getLogger(CompressionServletResponseWrapper.class);

    public CompressionServletResponseWrapper(HttpServletResponse response) {
        super(response);
        origResponse = response;

    }

    protected HttpServletResponse origResponse = null;

    protected ServletOutputStream stream = null;


    protected PrintWriter writer = null;

    protected String contentType = null;
 
    public void setContentType(String contentType) {

        this.contentType = contentType;
        origResponse.setContentType(contentType);
    }



    public ServletOutputStream createOutputStream() throws IOException {


        CompressionResponseStream stream = new CompressionResponseStream(origResponse);
   
        return stream;

    }

    /**
     * Finish a response.
     */
    public void finishResponse() {
        try {
        
        	
            if (writer != null) {
                writer.close();
            } else {
                if (stream != null)
                    stream.close();
            }
       
            
        } catch (IOException e) {
        }
    }


    /**
     * Flush the buffer and commit this response.
     *
     * @exception IOException if an input/output error occurs
     */
    public void flushBuffer() throws IOException {

        ((CompressionResponseStream)stream).flush();

    }

    /**
     * Return the servlet output stream associated with this Response.
     *
     * @exception IllegalStateException if <code>getWriter</code> has
     *  already been called for this response
     * @exception IOException if an input/output error occurs
     */
    public ServletOutputStream getOutputStream() throws IOException {

        if (writer != null)
            throw new IllegalStateException("getWriter() has already been called for this response");

        if (stream == null)
            stream = createOutputStream();
  

        return (stream);

    }

    /**
     * Return the writer associated with this Response.
     *
     * @exception IllegalStateException if <code>getOutputStream</code> has
     *  already been called for this response
     * @exception IOException if an input/output error occurs
     */
    public PrintWriter getWriter() throws IOException {

        if (writer != null)
            return (writer);

        if (stream != null)
            throw new IllegalStateException("getOutputStream() has already been called for this response");

        stream = createOutputStream();

        
        //String charset = getCharsetFromContentType(contentType);
        String charEnc = origResponse.getCharacterEncoding();
        log.debug("Content-Type="+origResponse.getContentType()+"  charset="+charEnc);
        // HttpServletResponse.getCharacterEncoding() shouldn't return null
        // according the spec, so feel free to remove that "if"
        if (charEnc != null) {
            writer = new PrintWriter(new OutputStreamWriter(stream, charEnc));
        } else {
            writer = new PrintWriter(stream);
        }
        
        return (writer);

    }

}

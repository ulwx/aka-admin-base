/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.github.ulwx.aka.admin.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;


public class CompressionResponseStream extends ServletOutputStream{

    protected ByteArrayOutputStream outstream=new ByteArrayOutputStream(1024);
    protected OutputStream gzipstream = new GZIPOutputStream(new BufferedOutputStream(outstream));
    protected boolean closed=false;
    protected HttpServletResponse response=null;
    
    public CompressionResponseStream(HttpServletResponse response) throws IOException{
    	
    	this.response = response;
    	 response.addHeader("Content-Encoding", "gzip");
    }

    public void close() throws IOException {

        gzipstream.close();
        this.closed=true;
        response.setHeader("Content-Length", outstream.size()+"");
        
        ServletOutputStream servletOutStream = response.getOutputStream();
        servletOutStream.write(outstream.toByteArray());
        servletOutStream.flush();
        servletOutStream.close();
    }


    public void flush() throws IOException {

        if (closed) {
            throw new IOException("Cannot flush a closed output stream");
        }
        gzipstream.flush();

    }




    public void write(int b) throws IOException {

        if (closed)
            throw new IOException("Cannot write to a closed output stream");

       
        writeToGZip(new byte[]{(byte)b}, 0, 1);

    }


 
    public void write(byte b[]) throws IOException {

        write(b, 0, b.length);

    }



    public void write(byte b[], int off, int len) throws IOException {



        if (closed)
            throw new IOException("Cannot write to a closed output stream");

        if (len == 0)
            return;

        // write direct to gzip
        writeToGZip(b, off, len);
    }

    public void writeToGZip(byte b[], int off, int len) throws IOException {
    
        gzipstream.write(b, off, len);
       

    }

    // -------------------------------------------------------- Package Methods


    /**
     * Has this response stream been closed?
     */
    public boolean closed() {

        return (this.closed);

    }


	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub
		
	}

}

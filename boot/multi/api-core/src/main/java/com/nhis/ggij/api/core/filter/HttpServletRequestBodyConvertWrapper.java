package com.nhis.ggij.api.core.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.function.Function;

public class HttpServletRequestBodyConvertWrapper extends HttpServletRequestWrapper {

//	private final ByteArrayInputStream capture;
	private ServletInputStream input;
	private PrintWriter writer;
	private byte[] bytes;
	public HttpServletRequestBodyConvertWrapper(HttpServletRequest request, Function<byte[], byte[]> action) throws IOException {
		super(request);
		bytes = IOUtils.toByteArray(request.getInputStream());
		bytes = action.apply(bytes);
		//capture = new ByteArrayOutputStream(request.getInputStream().
//		capture.
	}

	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return new ServletInputStreamImpl(bis);
	}



	class ServletInputStreamImpl extends ServletInputStream{
		private InputStream is;
		public ServletInputStreamImpl(InputStream bis){
			is = bis;
		}
		public int read() throws IOException {
			return is.read();
		}

		public int read(byte[] b) throws IOException {
			return is.read(b);
		}

		@Override
		public boolean isFinished() {
			return true;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener listener) {

		}
	}

}
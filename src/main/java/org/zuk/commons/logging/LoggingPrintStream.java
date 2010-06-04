package org.zuk.commons.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Consente la scrittura nel file di log da parte di classi che preferiscono
 * scrivere il debug in PrintStream, come, per es. {@link javax.mail.Session}.
 * <p>
 * 
 * 
 * 
 * @version 1.0
 * @since 1.6.10
 * @author <a href="mailto:stefano.zuccaro@postecom.it">Stefano Zuccaro</a>
 * 
 */
public class LoggingPrintStream extends PrintStream {
	private PostecomLogger logger;
	private POSTECOM_LOGGER_LEVEL level;

	public LoggingPrintStream(PostecomLogger logger, POSTECOM_LOGGER_LEVEL level) {
		super(NULL_OUTPUTSTREAM);
		this.logger = logger;
		this.level = level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] buf, int off, int len) {
		try {
			if (len > 1 && buf[off + len - 1] == '\n') {
				len--;
			}
			String message = new String(buf, off, len, "ISO-8859-1");
			logger.log(level, message);
		} catch (UnsupportedEncodingException e) {
			logger.error(e, "unable to write into logger");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintStream#write(int)
	 */
	@Override
	public void write(int b) {
		this.write(new byte[] { (byte) b }, 0, 1);
	}

	/**
	 * dummy {@link OutputStream}: fa nulla.
	 * <p>
	 * 
	 * 
	 */
	private final static OutputStream NULL_OUTPUTSTREAM = new OutputStream() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.OutputStream#write(int)
		 */
		@Override
		public void write(int b) throws IOException {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.OutputStream#close()
		 */
		@Override
		public void close() throws IOException {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.OutputStream#flush()
		 */
		@Override
		public void flush() throws IOException {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.OutputStream#write(byte[], int, int)
		 */
		@Override
		public void write(byte[] b, int off, int len) throws IOException {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.OutputStream#write(byte[])
		 */
		@Override
		public void write(byte[] b) throws IOException {
		}

	};

}

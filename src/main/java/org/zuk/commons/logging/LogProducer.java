package org.zuk.commons.logging;

/**
 * <code>LogProducer</code> implementations are used to produce the log output.
 * <p>
 * 
 * 
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * @since 1.6.5
 */
public interface LogProducer {

	/**
	 * Writes a line of log whith the {@link Throwable} stacktrace and the
	 * message.
	 * <p>
	 * 
	 * 
	 * @param t
	 * @param message
	 */
	public void log(Throwable t, String message);

	/**
	 * Writes a line og LOG with the passed message.
	 * <p>
	 * <p>
	 * 
	 * 
	 * @param message
	 */
	public void log(String message);
}

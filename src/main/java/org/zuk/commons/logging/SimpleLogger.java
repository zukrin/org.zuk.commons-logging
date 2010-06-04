package org.zuk.commons.logging;

import java.text.MessageFormat;

import org.zuk.commons.logging.SimpleLoggerFactory.SimpleLoggerBaseLoggerFactory;

/**
 * This logger implementation uses Java5 features to avoid the string sum
 * overhead before the log level evaluation. In other words, the class defines
 * methods to check if the log level is allowed and only when the check is
 * passed, the message is built from a pattern and a varargs list of arguments,
 * and the log is written.
 * <p>
 * The message is produced whith a {@link MessageFormat}: the pattern follows
 * all MessageFormat rules.
 * <p>
 * From version 1.1 the implementation choses to use log4j (if present) or java
 * logging (if log4j is absent).
 * <p>
 * <p>
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * @version 1.1
 * @since 1.5
 */
public abstract class SimpleLogger {

	private static SimpleLoggerFactory baseLoggerFactory;

	/**
	 * Returns always an implementation of BasicLogger.
	 * <p>
	 * 
	 * @param claz
	 * @return
	 */
	public synchronized static SimpleLogger getBaseLogger(Class<?> claz) {
		if (baseLoggerFactory == null) {
			baseLoggerFactory = new SimpleLoggerBaseLoggerFactory();
		}

		return baseLoggerFactory.getInstance(claz);
	}

	/**
	 * Returns a SimpleLogger associated to the hierarchy built with the class
	 * (and package) passed as argument.
	 * <p>
	 * 
	 * 
	 * @param claz
	 * @return
	 */
	public static SimpleLogger getLogger(Class<?> claz) {
		return SimpleLoggerFactory.getFactory().getInstance(claz);
	}

	/**
	 * 
	 * @param pattern
	 * @param objects
	 */
	public void trace(String pattern, Object... objects) {
		log(LOGGER_LEVEL.TRACE, pattern, objects);
	}

	/**
	 * 
	 * @param pattern
	 * @param objects
	 */
	public void debug(String pattern, Object... objects) {
		log(LOGGER_LEVEL.DEBUG, pattern, objects);
	}

	/**
	 * 
	 * @param pattern
	 * @param objects
	 */
	public void info(String pattern, Object... objects) {
		log(LOGGER_LEVEL.INFO, pattern, objects);
	}

	/**
	 * 
	 * @param pattern
	 * @param objects
	 */
	public void warn(String pattern, Object... objects) {
		log(LOGGER_LEVEL.WARN, pattern, objects);
	}

	/**
	 * 
	 * @param pattern
	 * @param objects
	 */
	public void error(String pattern, Object... objects) {
		log(LOGGER_LEVEL.ERROR, pattern, objects);
	}

	/**
	 * 
	 * @param pattern
	 * @param objects
	 */
	public void fatal(String pattern, Object... objects) {
		log(LOGGER_LEVEL.FATAL, pattern, objects);
	}

	/**
	 * 
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	public void trace(Throwable throwable, String pattern, Object... objects) {
		log(LOGGER_LEVEL.TRACE, throwable, pattern, objects);
	}

	/**
	 * 
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	public void debug(Throwable throwable, String pattern, Object... objects) {
		log(LOGGER_LEVEL.DEBUG, throwable, pattern, objects);
	}

	/**
	 * 
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	public void info(Throwable throwable, String pattern, Object... objects) {
		log(LOGGER_LEVEL.INFO, throwable, pattern, objects);
	}

	/**
	 * 
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	public void warn(Throwable throwable, String pattern, Object... objects) {
		log(LOGGER_LEVEL.WARN, throwable, pattern, objects);
	}

	/**
	 * 
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	public void error(Throwable throwable, String pattern, Object... objects) {
		log(LOGGER_LEVEL.ERROR, throwable, pattern, objects);
	}

	/**
	 * 
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	public void fatal(Throwable throwable, String pattern, Object... objects) {
		log(LOGGER_LEVEL.FATAL, throwable, pattern, objects);
	}

	/**
	 * 
	 * @param level
	 * @param pattern
	 * @param objects
	 */
	protected abstract void log(LOGGER_LEVEL level, String pattern, Object... objects);

	/**
	 * 
	 * @param level
	 * @param throwable
	 * @param pattern
	 * @param objects
	 */
	protected abstract void log(LOGGER_LEVEL level, Throwable throwable, String pattern, Object... objects);

	/**
	 * 
	 * @param level
	 * @return
	 */
	protected abstract boolean isEnabledFor(LOGGER_LEVEL level);

	/**
	 * 
	 * @param level
	 */
	public abstract void setLevel(LOGGER_LEVEL level);

	/**
	 * 
	 * @return
	 */
	public boolean isAllEnabled() {
		return isEnabledFor(LOGGER_LEVEL.ALL);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTraceEnabled() {
		return isEnabledFor(LOGGER_LEVEL.TRACE);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDebugEnabled() {
		return isEnabledFor(LOGGER_LEVEL.DEBUG);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isInfoEnabled() {
		return isEnabledFor(LOGGER_LEVEL.INFO);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isWarnEnabled() {
		return isEnabledFor(LOGGER_LEVEL.WARN);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isErrorEnabled() {
		return isEnabledFor(LOGGER_LEVEL.ERROR);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFatalEnabled() {
		return isEnabledFor(LOGGER_LEVEL.FATAL);
	}

}

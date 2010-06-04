package org.zuk.commons.logging;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

/**
 * Implementazione del logger per log4j.
 * <p>
 * 
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * @since 1.6.6
 */
public class SimpleLoggerLog4jImpl extends SimpleLogger {
	/*
	 * log4j usa questo parametro per trovare correttamente il numero di linea
	 * di codice dal quale parte il log.
	 */
	private static final String FQCN = SimpleLogger.class.getName() + ".";

	private Logger logger;

	/**
	 * 
	 * @param claz
	 */
	protected SimpleLoggerLog4jImpl(Class<?> claz) {
		logger = Logger.getLogger(claz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.zuk.commons.logging.SimpleLogger#log(org.zuk.commons.logging.LOGGER_LEVEL
	 * , java.lang.String, java.lang.Object[])
	 */
	@Override
	protected void log(LOGGER_LEVEL level, String pattern, Object... objects) {
		if (isEnabledFor(level)) {
			if (objects != null && objects.length > 0) {
				String message = MessageFormat.format(pattern, objects);
				logger.log(FQCN, level.getLog4jLevel(), message, null);
			}
			else {
				logger.log(FQCN, level.getLog4jLevel(), pattern, null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.zuk.commons.logging.SimpleLogger#log(org.zuk.commons.logging.LOGGER_LEVEL
	 * , java.lang.Throwable, java.lang.String, java.lang.Object[])
	 */
	@Override
	protected void log(LOGGER_LEVEL level, Throwable throwable, String pattern, Object... objects) {
		if (isEnabledFor(level)) {
			if (objects != null && objects.length > 0) {
				String message = MessageFormat.format(pattern, objects);
				logger.log(FQCN, level.getLog4jLevel(), message, throwable);
			}
			else {
				logger.log(FQCN, level.getLog4jLevel(), pattern, throwable);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.zuk.commons.logging.SimpleLogger#isEnabledFor(org.zuk.commons.logging
	 * .LOGGER_LEVEL)
	 */
	@Override
	protected boolean isEnabledFor(LOGGER_LEVEL level) {
		return logger.isEnabledFor(level.getLog4jLevel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.zuk.commons.logging.SimpleLogger#setLevel(org.zuk.commons.logging
	 * .LOGGER_LEVEL)
	 */
	@Override
	public void setLevel(LOGGER_LEVEL level) {
		logger.setLevel(level.getLog4jLevel());
	}

	/**
	 * 
	 * @return
	 */
	public Logger getLog4jLogger() {
		return logger;
	}
}

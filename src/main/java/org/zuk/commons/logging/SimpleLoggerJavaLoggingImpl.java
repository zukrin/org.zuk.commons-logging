package org.zuk.commons.logging;

import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * Implementazione del logger per java logging.
 * <p>
 * 
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * @since 1.6.6
 */
public class SimpleLoggerJavaLoggingImpl extends SimpleLogger {
	private Logger logger;

	/**
	 * 
	 * @param claz
	 */
	protected SimpleLoggerJavaLoggingImpl(Class<?> claz) {
		logger = Logger.getLogger(claz.getName());
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
				logger.log(level.getJavaLoggingLevel(), MessageFormat.format(pattern, objects));
			}
			else {
				logger.log(level.getJavaLoggingLevel(), pattern);
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
				logger.log(level.getJavaLoggingLevel(), MessageFormat.format(pattern, objects), throwable);
			}
			else {
				logger.log(level.getJavaLoggingLevel(), pattern, throwable);
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
		return logger.isLoggable(level.getJavaLoggingLevel());
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
		logger.setLevel(level.getJavaLoggingLevel());
	}
}

package org.zuk.commons.logging;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

/**
 * Implementazione del logger per log4j.
 * <p>
 * 
 * 
 * @author <a
 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro@postecom.
 *         it">Stefano Zuccaro</a>
 * @since 1.6.6
 */
public class PostecomLoggerLog4jImpl extends PostecomLogger
{
	/*
	 * log4j usa questo parametro per trovare correttamente il numero di linea di
	 * codice dal quale parte il log.
	 */
	private static final String FQCN = PostecomLogger.class.getName() + ".";

	private Logger logger;

	protected PostecomLoggerLog4jImpl(Class<?> claz)
	{
		logger = Logger.getLogger(claz);
	}

	@Override
	protected void log(POSTECOM_LOGGER_LEVEL level, String pattern, Object... objects)
	{
		if (isEnabledFor(level))
		{
			if (objects != null && objects.length > 0)
			{
				String message = MessageFormat.format(pattern, objects);
				logger.log(FQCN, level.getLog4jLevel(), message, null);
			}
			else
			{
				logger.log(FQCN, level.getLog4jLevel(), pattern, null);
			}
		}
	}

	@Override
	protected void log(POSTECOM_LOGGER_LEVEL level, Throwable throwable, String pattern, Object... objects)
	{
		if (isEnabledFor(level))
		{
			if (objects != null && objects.length > 0)
			{
				String message = MessageFormat.format(pattern, objects);
				logger.log(FQCN, level.getLog4jLevel(), message, throwable);
			}
			else
			{
				logger.log(FQCN, level.getLog4jLevel(), pattern, throwable);
			}
		}
	}

	@Override
	protected boolean isEnabledFor(POSTECOM_LOGGER_LEVEL level)
	{
		return logger.isEnabledFor(level.getLog4jLevel());
	}

	@Override
	public void setLevel(POSTECOM_LOGGER_LEVEL level)
	{
		logger.setLevel(level.getLog4jLevel());
	}

	public Logger getLog4jLogger()
	{
		return logger;
	}
}

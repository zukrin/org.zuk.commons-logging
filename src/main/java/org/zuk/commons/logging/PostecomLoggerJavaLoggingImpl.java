package org.zuk.commons.logging;

import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * Implementazione del logger per java logging.
 * <p>
 * 
 * 
 * @author <a
 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro@postecom.
 *         it">Stefano Zuccaro</a>
 * @since 1.6.6
 */
public class PostecomLoggerJavaLoggingImpl extends PostecomLogger
{
	private Logger logger;

	protected PostecomLoggerJavaLoggingImpl(Class<?> claz)
	{
		logger = Logger.getLogger(claz.getName());
	}

	@Override
	protected void log(POSTECOM_LOGGER_LEVEL level, String pattern, Object... objects)
	{
		if (isEnabledFor(level))
		{
			if (objects != null && objects.length > 0)
			{
				logger.log(level.getJavaLoggingLevel(), MessageFormat.format(pattern, objects));
			}
			else
			{
				logger.log(level.getJavaLoggingLevel(), pattern);
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
				logger.log(level.getJavaLoggingLevel(), MessageFormat.format(pattern, objects), throwable);
			}
			else
			{
				logger.log(level.getJavaLoggingLevel(), pattern, throwable);
			}
		}
	}

	@Override
	protected boolean isEnabledFor(POSTECOM_LOGGER_LEVEL level)
	{
		return logger.isLoggable(level.getJavaLoggingLevel());
	}

	@Override
	public void setLevel(POSTECOM_LOGGER_LEVEL level)
   {
		logger.setLevel(level.getJavaLoggingLevel());
   }
}

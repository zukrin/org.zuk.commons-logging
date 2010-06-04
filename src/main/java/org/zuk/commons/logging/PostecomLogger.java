package org.zuk.commons.logging;

import it.postecom.commons.dynamicconfiguration.IConfigurable;

import java.text.MessageFormat;

/**
 * Logger che sfrutta le nuove features di Java5 per evitare l'overhead della
 * concatenazione di stringhe prima della valutazione del livello del log. In
 * pratica la classe definisce dei metodi che effettuano il controllo sul
 * livello del log e solo se il log richiesto ha livello compatibile il
 * messaggio viene costruito a partire del pattern e dall'elenco a lunghezza
 * variabile di parametri.
 * <p>
 * La classe utilizza {@link MessageFormat} per costruire i messaggi di log.
 * <p>
 * 
 * Dalla versione 1.1 non dipende pi&ugrave; da log4j ma, indifferentemente
 * log4j e java logging a seconda che siano stati specificati i parametri:
 * <ul>
 * <li><code>PostecomLogging.log4j.enable=true</code></li>
 * <li><code>PostecomLogging.java.enable=true</code></li>
 * <li><code>PostecomLogging.base.enable=true</code></li>
 * </ul>
 * nella configurazione. Il terzo utilizza java logging per scrivere nel
 * server.log degli app.server di sun. Se nessuno dei tre &egrave; presente
 * prova ad istanziare log4j e se non lo trova passa a java logging.
 * <p>
 * 
 * @author stefano (zuck.24/set/07)
 * @version 1.1
 * @since 1.5
 */
public abstract class PostecomLogger {
	/**
	 * Factory per i logger.
	 * <p>
	 * Implementa {@link IConfigurable} per riconfigurare se stesso al cambio
	 * della configurazione.
	 * <p>
	 * 
	 * @author <a
	 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro
	 * @postecom.it">Stefano Zuccaro</a>
	 * 
	 */
	private static class PostecomLoggerFactory {
		private static PostecomLoggerFactory factory;

		public synchronized static PostecomLoggerFactory getFactory() {
			if (factory == null) {
				PostecomLogger logger = PostecomLogger.getBaseLogger(PostecomLoggerFactory.class);

				boolean log4jPresent = true;
				boolean chooseLog4j = false;
				boolean chooseJava = false;
				boolean chooseBase = false;

				logger.info("PostecomLoggerFactory configuration");
				try {
					Class.forName("org.apache.log4j.Level");
				}
				catch (ClassNotFoundException e) {
					log4jPresent = false;
				}

				logger.info("\n\tlog4jPresent={0}\n\tchooseLog4j={1}\n\tchooseJava={2}\n\tchooseBase={3}", log4jPresent, chooseLog4j, chooseJava, chooseBase);

				if (chooseLog4j && log4jPresent) {
					factory = new PostecomLoggerLog4jFactory();
				}
				else if (chooseBase) {
					factory = new PostecomLoggerBaseLoggerFactory();
				}
				else if (chooseJava) {
					factory = new PostecomLoggerJavaLoggingFactory();
				}
				else if (log4jPresent) {
					factory = new PostecomLoggerLog4jFactory();
				}
				else {
					factory = new PostecomLoggerJavaLoggingFactory();
				}

				logger.info("choosen factory class: {0}", factory.getClass().getName());
			}

			return factory;
		}

		private PostecomLoggerFactory() {

		}

		protected PostecomLogger getInstance(Class<?> claz) {
			return null;
		}
	}

	private static PostecomLoggerFactory baseLoggerFactory;

	/**
	 * Restituisce un logger sul BaseLogger: per sjsas si tratta di server.log
	 * 
	 * @param claz
	 * @return
	 */
	public synchronized static PostecomLogger getBaseLogger(Class<?> claz) {
		if (baseLoggerFactory == null) {
			baseLoggerFactory = new PostecomLoggerBaseLoggerFactory();
		}

		return baseLoggerFactory.getInstance(claz);
	}

	/**
	 * Implementazione per log4j
	 * 
	 * @author <a
	 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro
	 * @postecom.it">Stefano Zuccaro</a>
	 * 
	 */
	private static class PostecomLoggerLog4jFactory extends PostecomLoggerFactory {
		@Override
		public PostecomLogger getInstance(Class<?> claz) {
			return new PostecomLoggerLog4jImpl(claz);
		}
	}

	/**
	 * Implementazione per javalogging
	 * 
	 * @author <a
	 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro
	 * @postecom.it">Stefano Zuccaro</a>
	 * 
	 */
	private static class PostecomLoggerJavaLoggingFactory extends PostecomLoggerFactory {
		@Override
		public PostecomLogger getInstance(Class<?> claz) {
			return new PostecomLoggerJavaLoggingImpl(claz);
		}
	}

	/**
	 * implementeazione che scirve nel server.log
	 * 
	 * @author <a
	 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro
	 * @postecom.it">Stefano Zuccaro</a>
	 * 
	 */
	private static class PostecomLoggerBaseLoggerFactory extends PostecomLoggerFactory {
		@Override
		public PostecomLogger getInstance(Class<?> claz) {
			return new PostecomLoggerBaseLoggerImpl(claz);
		}
	}

	/**
	 * Restituisce un logger associato alla classe passata per argomento.
	 * <p>
	 * 
	 * 
	 * @param claz
	 * @return
	 */
	public static PostecomLogger getLogger(Class<?> claz) {
		return PostecomLoggerFactory.getFactory().getInstance(claz);
	}

	public void trace(String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.TRACE, pattern, objects);
	}

	public void debug(String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.DEBUG, pattern, objects);
	}

	public void info(String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.INFO, pattern, objects);
	}

	public void warn(String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.WARN, pattern, objects);
	}

	public void error(String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.ERROR, pattern, objects);
	}

	public void fatal(String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.FATAL, pattern, objects);
	}

	public void trace(Throwable throwable, String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.TRACE, throwable, pattern, objects);
	}

	public void debug(Throwable throwable, String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.DEBUG, throwable, pattern, objects);
	}

	public void info(Throwable throwable, String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.INFO, throwable, pattern, objects);
	}

	public void warn(Throwable throwable, String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.WARN, throwable, pattern, objects);
	}

	public void error(Throwable throwable, String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.ERROR, throwable, pattern, objects);
	}

	public void fatal(Throwable throwable, String pattern, Object... objects) {
		log(POSTECOM_LOGGER_LEVEL.FATAL, throwable, pattern, objects);
	}

	protected abstract void log(POSTECOM_LOGGER_LEVEL level, String pattern, Object... objects);

	protected abstract void log(POSTECOM_LOGGER_LEVEL level, Throwable throwable, String pattern, Object... objects);

	protected abstract boolean isEnabledFor(POSTECOM_LOGGER_LEVEL level);

	public abstract void setLevel(POSTECOM_LOGGER_LEVEL level);

	public boolean isAllEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.ALL);
	}

	public boolean isTraceEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.TRACE);
	}

	public boolean isDebugEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.DEBUG);
	}

	public boolean isInfoEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.INFO);
	}

	public boolean isWarnEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.WARN);
	}

	public boolean isErrorEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.ERROR);
	}

	public boolean isFatalEnabled() {
		return isEnabledFor(POSTECOM_LOGGER_LEVEL.FATAL);
	}

}

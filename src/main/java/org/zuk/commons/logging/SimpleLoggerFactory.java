package org.zuk.commons.logging;

import java.util.Properties;

/**
 * Instances factory
 * <p>
 * It uses a basic logging instance to show its own log.
 * <p>
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * 
 */
public class SimpleLoggerFactory {
	private static SimpleLoggerFactory factory;

	static boolean chooseLog4j = false;
	static boolean chooseJava = false;
	static boolean chooseBase = false;

	public synchronized static void configure(Properties properties) {
		if (factory != null) {
			throw new IllegalStateException("Factory already configured");
		}

		chooseLog4j = "true".equalsIgnoreCase(properties.getProperty("SimpleLoggerFactory.chooseLog4j"));
		chooseJava = "true".equalsIgnoreCase(properties.getProperty("SimpleLoggerFactory.chooseJava"));
		chooseBase = "true".equalsIgnoreCase(properties.getProperty("SimpleLoggerFactory.chooseBase"));
	}

	/**
	 * Returns the implementation following the rules:<br/>
	 * if one of <code>SimpleLoggerFactory.chooseLog4j</code>,
	 * <code>SimpleLoggerFactory.chooseJava</code> or
	 * <code>SimpleLoggerFactory.chooseBase</code> is <code>true</code> the
	 * relative implementation of SimpleLogger is returned; otherwise, if an
	 * implementation of log4j is available, it's the first choice, followed by
	 * java logging.
	 * <p>
	 * 
	 * 
	 * @return
	 */
	public synchronized static SimpleLoggerFactory getFactory() {
		if (factory == null) {
			SimpleLogger logger = SimpleLogger.getBaseLogger(SimpleLoggerFactory.class);

			boolean log4jPresent = true;

			logger.info("SimpleLoggerFactory configuration");
			try {
				Class.forName("org.apache.log4j.Level");
			} catch (ClassNotFoundException e) {
				log4jPresent = false;
			}

			logger.info("\n\tlog4jPresent={0}\n\tchooseLog4j={1}\n\tchooseJava={2}\n\tchooseBase={3}", log4jPresent, chooseLog4j, chooseJava, chooseBase);

			if (chooseLog4j && log4jPresent) {
				factory = new SimpleLoggerLog4jFactory();
			}
			else if (chooseBase) {
				factory = new SimpleLoggerBaseLoggerFactory();
			}
			else if (chooseJava) {
				factory = new SimpleLoggerJavaLoggingFactory();
			}
			else if (log4jPresent) {
				factory = new SimpleLoggerLog4jFactory();
			}
			else {
				factory = new SimpleLoggerJavaLoggingFactory();
			}

			logger.info("choosen factory class: {0}", factory.getClass().getName());
		}

		return factory;
	}

	/**
	 * 
	 */
	private SimpleLoggerFactory() {}

	/**
	 * 
	 * @param claz
	 * @return
	 */
	protected SimpleLogger getInstance(Class<?> claz) {
		return null;
	}

	/**
	 * Implementation for log4j
	 * 
	 * @author <a
	 *         href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a
	 *         >
	 * 
	 */
	public static class SimpleLoggerLog4jFactory extends SimpleLoggerFactory {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.zuk.commons.logging.SimpleLoggerFactory#getInstance(java.lang
		 * .Class)
		 */
		@Override
		public SimpleLogger getInstance(Class<?> claz) {
			return new SimpleLoggerLog4jImpl(claz);
		}
	}

	/**
	 * Implementation for java util logging
	 * 
	 * @author <a
	 *         href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a
	 *         >
	 * 
	 */
	public static class SimpleLoggerJavaLoggingFactory extends SimpleLoggerFactory {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.zuk.commons.logging.SimpleLoggerFactory#getInstance(java.lang
		 * .Class)
		 */
		@Override
		public SimpleLogger getInstance(Class<?> claz) {
			return new SimpleLoggerJavaLoggingImpl(claz);
		}
	}

	/**
	 * Implementation for System.out
	 * 
	 * @author <a
	 *         href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a
	 *         >
	 * 
	 */
	public static class SimpleLoggerBaseLoggerFactory extends SimpleLoggerFactory {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.zuk.commons.logging.SimpleLoggerFactory#getInstance(java.lang
		 * .Class)
		 */
		@Override
		public SimpleLogger getInstance(Class<?> claz) {
			return new SimpleLoggerBasicLoggerImpl(claz);
		}
	}
}

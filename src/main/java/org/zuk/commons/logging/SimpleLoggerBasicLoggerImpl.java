package org.zuk.commons.logging;

import java.text.MessageFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Logger minimalista utile per effettuare log in contesti dove non e' presente
 * [o non e' ancora configurato] il log applicativo (tipicamente log4j).
 * <p>
 * 
 * Il <code>PostecomLoggerBaseLoggerImpl</code> si appoggia alle implementazioni
 * di {@link LogProducer} per effettuare il log e di default utilizza un
 * LogProducer che si appoggia a java.logging per produrre il log in server.log
 * degli application server di Sun.
 * <p>
 * E' possibile implementare il proprio <code>LogProducer</code> ed utilizzare
 * il metodo {@link SimpleLoggerBasicLoggerImpl#setProducer(LogProducer)} per
 * sostituirlo a quello di default.
 * <p>
 * A differenza di log4j (e altri) non richiede la gerarchia alla creazione e si
 * occupa di ricostruirla in autonomia dallo stacktrace opportunamente creato.
 * <p>
 * Come {@link SimpleLogger} espone metodi di logging che accettano un numero
 * variabile di argomenti e sostisuisce i valori passati ai mark inseriti
 * opportunamente nel messaggio. Per esempio:<br>
 * <code>info("esempio di log fatto da {0} per {1}","pippo","pluto");</code>
 * <p>
 * Non utilizza file di configurazione: deve essere configurato a runtime.
 * <p>
 * Di default ha il livello impostato ad <code>INFO</code>.
 * <p>
 * 
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * @since 1.6.6
 */
public class SimpleLoggerBasicLoggerImpl extends SimpleLogger {

	/**
	 * Costruttore.
	 * <p>
	 * Imposta il {@link LogProducer} di default (output attraverso
	 * {@link Logger} con subsystem <code>javax.enterprise.system</code> in modo
	 * da produrre un risultato nel server.log degli application server di Sun)
	 * <p>
	 * Imposta il livello di logging a INFO.
	 * <p>
	 * 
	 */
	private static ConsoleHandler handler;

	static {
		handler = new EasyConsoleHandler();
		handler.setLevel(Level.ALL);
	}

	/**
	 * 
	 * @param claz
	 */
	protected SimpleLoggerBasicLoggerImpl(Class<?> claz) {}

	/**
	 * metodo generico per il logging.
	 * <p>
	 * 
	 * 
	 * @param level
	 * @param t
	 * @param pattern
	 * @param args
	 */
	@Override
	protected void log(LOGGER_LEVEL level, Throwable t, String pattern, Object... args) {
		if (isEnabledFor(level)) {

			Exception e = new Exception();
			StackTraceElement[] ste = e.getStackTrace();

			String fName = "???";
			String mName = "???";
			int l = -1;

			int i = 0;
			while (i < ste.length) {
				String steCName = ste[i].getClassName();
				String thisCName = this.getClass().getName();
				String superCName = this.getClass().getSuperclass().getName();
				if (!steCName.equals(thisCName) && !steCName.equals(superCName)) {
					fName = ste[i].getClassName();
					l = ste[i].getLineNumber();
					mName = ste[i].getMethodName() + "(" + l + ")";
					break;
				}
				i++;
			}
			String message = MessageFormat.format(pattern, args);

			LogRecord lr = new LogRecord(level.getJavaLoggingLevel(), message);
			lr.setSourceClassName(fName);
			lr.setSourceMethodName(mName);
			if (t != null) {
				lr.setThrown(t);
			}

			handler.publish(lr);
		}
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
		log(level, null, pattern, objects);

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
		return level.getJavaLoggingLevel().intValue() >= handler.getLevel().intValue();
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
		handler.setLevel(level.getJavaLoggingLevel());

	}
}

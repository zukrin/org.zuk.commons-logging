package org.zuk.commons.logging;

/**
 * Le implementazioni di <code>LogProduce</code> si occupano di produrre
 * fisicamente il log per il <code>PostecomLoggerBaseLoggerImpl</code>.
 * <p>
 * 
 * 
 * @author <a
 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro@postecom.
 *         it">Stefano Zuccaro</a>
 * @since 1.6.5
 */
public interface LogProducer
{
	/**
	 * Produce una riga di log con un <code>Throwable</code>, di cui si occupa di
	 * scrivere lo stacktrace, e un messaggio <code>message</code>.
	 * <p>
	 * 
	 * 
	 * @param t
	 * @param message
	 */
	public void log(Throwable t, String message);

	/**
	 * Produce una riga di log scrivendo il messaggio <code>message</code>.
	 * <p>
	 * 
	 * 
	 * @param message
	 */
	public void log(String message);
}

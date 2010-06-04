package org.zuk.commons.logging;

/**
 * Tabella di equivalenza:<br>
 * <table border ="1">
 * <tr>
 * <th>POSTECOM_LOGGER_LEVEL</th>
 * <th>log4j</th>
 * <th>java logging</th>
 * </tr>
 * <tr>
 * <td>ALL</td>
 * <td>ALL</td>
 * <td>ALL</td>
 * </tr>
 * <tr>
 * <td>TRACE</td>
 * <td>TRACE</td>
 * <td>FINER</td>
 * </tr>
 * <tr>
 * <td>DEBUG</td>
 * <td>DEBUG</td>
 * <td>FINE</td>
 * </tr>
 * <tr>
 * <td>INFO</td>
 * <td>INFO</td>
 * <td>INFO</td>
 * </tr>
 * <tr>
 * <td>WARN</td>
 * <td>WARN</td>
 * <td>WARNING</td>
 * </tr>
 * <tr>
 * <td>ERROR</td>
 * <td>ERROR</td>
 * <td>SEVERE</td>
 * </tr>
 * <tr>
 * <td>FATAL</td>
 * <td>FATAL</td>
 * <td>SEVERE</td>
 * </tr>
 * </table>
 * 
 * 
 * @author <a
 *         href="="mailto:stefano.zuccaro@postecom.it" alt="stefano.zuccaro@postecom.
 *         it">Stefano Zuccaro</a>
 * @since 1.6.6
 */
public enum LOGGER_LEVEL {
	ALL(1, org.apache.log4j.Level.ALL, java.util.logging.Level.ALL), TRACE(2, org.apache.log4j.Level.TRACE, java.util.logging.Level.FINER), DEBUG(3,
		org.apache.log4j.Level.DEBUG, java.util.logging.Level.FINE), INFO(10, org.apache.log4j.Level.INFO, java.util.logging.Level.INFO), WARN(100,
		org.apache.log4j.Level.WARN, java.util.logging.Level.WARNING), ERROR(200, org.apache.log4j.Level.ERROR, java.util.logging.Level.SEVERE), FATAL(500,
		org.apache.log4j.Level.FATAL, java.util.logging.Level.SEVERE), OFF(-1, org.apache.log4j.Level.OFF, java.util.logging.Level.OFF);

	private int priority;

	private org.apache.log4j.Level log4jLevel;

	private java.util.logging.Level javaLoggingLevel;

	POSTECOM_LOGGER_LEVEL(int priority, org.apache.log4j.Level log4jLevel, java.util.logging.Level javaLoggingLevel) {
		this.priority = priority;
		this.log4jLevel = log4jLevel;
		this.javaLoggingLevel = javaLoggingLevel;
	}

	public int getPriority() {
		return priority;
	}

	public org.apache.log4j.Level getLog4jLevel() {
		return log4jLevel;
	}

	public java.util.logging.Level getJavaLoggingLevel() {
		return javaLoggingLevel;
	}
}

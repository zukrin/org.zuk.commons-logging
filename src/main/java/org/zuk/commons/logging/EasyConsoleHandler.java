package org.zuk.commons.logging;

import java.util.logging.ConsoleHandler;

/**
 * {@link ConsoleHandler} implementation that writes into {@link System#out}.
 * <p>
 * 
 * 
 * @author <a href="="mailto:zukrin@gmail.com" alt="zukrin@gmail.com">zukrin</a>
 * @version $Id: $
 */
public class EasyConsoleHandler extends ConsoleHandler {
	public EasyConsoleHandler() {
		super();
		setOutputStream(System.out);
	}

}

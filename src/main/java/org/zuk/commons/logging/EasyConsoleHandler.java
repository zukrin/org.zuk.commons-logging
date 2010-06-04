package org.zuk.commons.logging;

import java.util.logging.ConsoleHandler;

public class EasyConsoleHandler extends ConsoleHandler {
	public EasyConsoleHandler()
	{
		super();
		setOutputStream(System.out);
	}

}

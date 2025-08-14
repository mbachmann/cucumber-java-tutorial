package com.example.utils;

import org.slf4j.*;

/**
 * HasLogger is a feature interface that provides Logging capability for anyone
 * implementing it where logger needs to operate in serializable environment
 * without being static.
 */
public interface HasLogger {

	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
}
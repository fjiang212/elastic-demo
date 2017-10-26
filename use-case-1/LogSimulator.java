package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class LogSimulator {

private static Logger mLogger = Logger.getLogger(LogSimulator.class);
private static final String COMMA_DELIMITED_RE = "\\s*,\\s*";
private static int SLEEP_TIME = Integer.getInteger("example.logsimulator.sleeptime", 2);
private static int MAX_LOG_COUNT = Integer.getInteger("example.logsimulator.logcount", 10000);
private static String LOG_CONFIG = System.getProperty("example.logsimulator.config", "defaultLog.properties");

private Properties mLogConfigProp = new Properties();
private long mStartTime;
	
public LogSimulator() throws FileNotFoundException, IOException {
	// Initialize log4j
	Properties log4jProp = new Properties();
	log4jProp.load(new FileInputStream("log4j.properties"));

	System.out.println(log4jProp);
	PropertyConfigurator.configure(log4jProp);

	// Initialize Log config
	mLogConfigProp.load(new FileInputStream(LOG_CONFIG));}

public void generateLog() throws InterruptedException {
	final String[] logLevels = mLogConfigProp.getProperty("level").split(COMMA_DELIMITED_RE);

	int count = 1;
	mStartTime = System.currentTimeMillis();

	while (count < MAX_LOG_COUNT) {
		for (String level : logLevels) {
			writeLogEvent(level, count);
			count++;
		}

		if ("true".equalsIgnoreCase(mLogConfigProp.getProperty("exception"))) {
			writeLogEvent("EXCEPTION", count);
			count++;
		}

		Thread.sleep(SLEEP_TIME);
	}
}

private void writeLogEvent(String level, int count) {
	if (count > MAX_LOG_COUNT) {
		return;
	}
	String messsage = generateMessage(count);
	
	switch (level) {
	case "DEBUG":
		mLogger.debug(messsage);
		break;
	case "INFO":
		mLogger.info(messsage);
		break;
	case "WARN":
		mLogger.warn(messsage);
		break;
	case "ERROR":
		mLogger.error(messsage);
		break;
	case "FATAL":
		mLogger.fatal(messsage);
		break;
	case "EXCEPTION":
		mLogger.error(messsage, new NullPointerException());
		break;
	}
	
	if (count % 1000 == 0) {
		System.out.format("Log count: %d, Throughput: %d count/second \n", count,
				(count * 1000) / (System.currentTimeMillis() - mStartTime));
	}
}

private String generateMessage(int count) {
	if ("true".equalsIgnoreCase(mLogConfigProp.getProperty("counter"))) {
		return mLogConfigProp.getProperty("message") + " " + count;
	} 
	else {
		return mLogConfigProp.getProperty("message");
	}
}


public static void main(String[] args) throws InterruptedException, IOException {
	LogSimulator simulator = new LogSimulator();
	simulator.generateLog();
}
}

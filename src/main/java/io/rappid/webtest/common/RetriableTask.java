package io.rappid.webtest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

/**
 * User: tony
 * Date: 22.10.13
 * Time: 18:13
 */

/**
 * Most of the code was taken from these two websites.
 * http://fahdshariff.blogspot.de/2009/08/retrying-operations-in-java.html
 * http://biotext.org.uk/retriabletask-a-generic-wrapper-for-retrying-operations-in-java/
 */
public class RetriableTask<T> implements Callable<T> {

    private final Callable<T> task;
    public static final int DEFAULT_NUMBER_OF_RETRIES = 15;
    public static final long DEFAULT_WAIT_TIME = 10000;

    private final Logger taskLog;

    private final int numberOfRetries; // total number of tries
    private int numberOfTriesLeft; // number of tries left
    private final long timeToWait; // wait interval

    public RetriableTask(Logger log, Callable<T> task) {
        this(DEFAULT_NUMBER_OF_RETRIES, DEFAULT_WAIT_TIME, log, task);
    }

    public RetriableTask(int numberOfRetries, long timeToWait, Logger log, Callable<T> task) {
        this.numberOfRetries = numberOfRetries;
        this.numberOfTriesLeft = numberOfRetries;
        this.timeToWait = timeToWait;
        this.task = task;
        this.taskLog = log == null ? LoggerFactory.getLogger(RetriableTask.class) : log;
    }

    public T call() throws Exception {
        while (true) {
            try {
                return task.call();
            } catch (InterruptedException e) {
                throw e;
            } catch (CancellationException e) {
                throw e;
            } catch (Exception e) {
                numberOfTriesLeft--;
                taskLog.info("Exception found while retrying, message: " + e.getMessage());
                taskLog.info("Retries left: " + numberOfTriesLeft);
                if (numberOfTriesLeft == 0) {
                    taskLog.info("Throwing exception after " + numberOfRetries + " tries, message: "
                            + e.getMessage());
                    throw e;
                }
                Thread.sleep(timeToWait);
            } catch (AssertionError a) {
                numberOfTriesLeft--;
                taskLog.info("AssertionError found while retrying, message: " + a.getMessage());
                taskLog.info("Retries left: " + numberOfTriesLeft);
                if (numberOfTriesLeft == 0) {
                    taskLog.info("Throwing assertion error after " + numberOfRetries + " tries, message: "
                            + a.getMessage());
                    throw a;
                }
                Thread.sleep(timeToWait);
            }
        }
    }
}

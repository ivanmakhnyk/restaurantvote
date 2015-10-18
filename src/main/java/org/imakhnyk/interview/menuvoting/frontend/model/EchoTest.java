package org.imakhnyk.interview.menuvoting.frontend.model;

/**
 * DTO model for frontend
 * 
 * @author Ivan Makhnyk
 *
 */
public class EchoTest {

    private final long counter;
    private final String content;

    public EchoTest(long counter, String content) {
        this.counter = counter;
        this.content = content;
    }

    public long getCounter() {
        return counter;
    }

    public String getContent() {
        return content;
    }
}
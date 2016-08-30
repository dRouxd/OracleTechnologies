package com.danny.tipcalculator;

/**
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class TipException extends Exception {
    public TipException(Throwable throwable) {
        super(throwable);
    }
    public TipException(String message) {
        super(message);
    }
}

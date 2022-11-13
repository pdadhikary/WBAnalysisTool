package ca.yorku.eecs3311.team09.exceptions;

/**
 * An exception that signals that the selected analysis and plot are not compatible.
 */
public class IncompatibleAnalysisException extends RuntimeException {
    public IncompatibleAnalysisException(String msg) {
        super(msg);
    }
}

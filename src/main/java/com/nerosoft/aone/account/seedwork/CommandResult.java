package com.nerosoft.aone.account.seedwork;

/**
 * A generic class to encapsulate the result of a command execution along with an optional message.
 *
 * @param <R> The type of the result.
 */
public class CommandResult<R> {
    private R result;
    private String message;

    public CommandResult(R result, String message) {
        this.result = result;
        this.message = message;
    }

    public CommandResult(R result) {
        this.result = result;
        this.message = null;
    }

    public R getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}

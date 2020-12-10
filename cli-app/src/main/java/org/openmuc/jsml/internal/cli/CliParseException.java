/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */
package org.openmuc.jsml.internal.cli;

public final class CliParseException extends Exception {

    private static final long serialVersionUID = -5162894897245715377L;

    public CliParseException() {
        super();
    }

    public CliParseException(String s) {
        super(s);
    }

    public CliParseException(Throwable cause) {
        super(cause);
    }

    public CliParseException(String s, Throwable cause) {
        super(s, cause);
    }

}

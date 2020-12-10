package org.openmuc.jsml.app;

public class Print {

    static void println(String... strings) {
        System.out.println(build(strings));
    }

    static void printErr(String... strings) {
        System.err.println(build(strings));
    }

    private static String build(String... strings) {
        if (strings != null && strings.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (String string : strings) {
                sb.append(string);
            }
            return sb.toString();
        }
        else if (strings != null && strings.length == 1) {
            return strings[0];
        }
        else {
            return "";
        }
    }

    private Print() {
        // hide it
    }

}

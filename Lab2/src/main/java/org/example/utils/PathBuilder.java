package org.example.utils;

import java.io.File;

public final class PathBuilder {
    private PathBuilder() {}

    public static String getPath(String... path) {
        return String.join(File.separator, path);
    }
}

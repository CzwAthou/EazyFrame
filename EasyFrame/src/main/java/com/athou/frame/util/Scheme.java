/*
 * Copyright (c) 2016  athou（cai353974361@163.com）.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.athou.frame.util;

import java.util.Locale;

/**
 * Created by athou on 2016/12/2.
 */

public enum Scheme {
    HTTP("http"), HTTPS("https"), FILE("file"), CONTENT("content"), ASSETS("assets"), DRAWABLE("drawable"), UNKNOWN("");

    private String scheme;
    private String uriPrefix;

    Scheme(String scheme) {
        this.scheme = scheme;
        uriPrefix = scheme + "://";
    }

    /**
     * Defines scheme of incoming URI
     *
     * @param uri URI for scheme detection
     * @return Scheme of incoming URI
     */
    public static Scheme ofUri(String uri) {
        if (uri != null) {
            for (Scheme s : values()) {
                if (s.belongsTo(uri)) {
                    return s;
                }
            }
        }
        return UNKNOWN;
    }

    private boolean belongsTo(String uri) {
        return uri.toLowerCase(Locale.US).startsWith(uriPrefix);
    }

    /** Appends scheme to incoming path */
    public String wrap(String path) {
        return uriPrefix + path;
    }

    /** Removed scheme part ("scheme://") from incoming URI */
    public String crop(String uri) {
        if (!belongsTo(uri)) {
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", uri, scheme));
        }
        return uri.substring(uriPrefix.length());
    }
}
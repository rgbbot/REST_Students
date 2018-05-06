package com.tdmax.university.util;

import java.util.Collections;
import java.util.Optional;

public final class Util {
    public static <T> Iterable<T> toIterable(Optional<T> o) {
        return o.map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

}

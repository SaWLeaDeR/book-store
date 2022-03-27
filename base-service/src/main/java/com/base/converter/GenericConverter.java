package com.base.converter;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<S, T> {

    T convert(S source);

    default List<T> convert(List<S> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        }
        return sources.stream().map(this::convert).collect(Collectors.toList());
    }
}

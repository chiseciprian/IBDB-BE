package ro.fasttrackit.utils;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ModelMappers<A, E> {
    A toApi(E source);

    E toDb(A source);

    default List<A> toApi(Collection<E> all) {
        return all.stream()
                .map(this::toApi)
                .collect(toList());
    }

    default List<E> toDb(Collection<A> all) {
        return all.stream()
                .map(this::toDb)
                .collect(toList());
    }
}

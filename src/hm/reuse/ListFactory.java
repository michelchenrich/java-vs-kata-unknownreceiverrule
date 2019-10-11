package hm.reuse;

import java.util.List;

public interface ListFactory {
    <E> List<E> makeList();
}

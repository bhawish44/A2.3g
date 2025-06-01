package unisa.dse.a2.students;

/**
 * Corrected NodeGeneric class matching assignment requirements
 */
public class NodeGeneric<T> {

    public NodeGeneric<T> next;
    public NodeGeneric<T> prev;

    private T t;

    public NodeGeneric(NodeGeneric<T> next, NodeGeneric<T> prev, T node) {
        this.next = next;
        this.prev = prev;
        this.t = node;
    }

    // Required method for item access in DSEListGeneric
    public T getItem() {
        return t;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (!(other instanceof NodeGeneric<?>))
            return false;

        return t.equals(((NodeGeneric<?>) other).getItem());
    }

    @Override
    public int hashCode() {
        if (t == null)
            return 0;
        return t.hashCode();
    }
}

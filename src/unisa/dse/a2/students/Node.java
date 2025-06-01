package unisa.dse.a2.students;

/**
 * Reference implementation of Node (non-generic)
 * Used by DSEList only
 */
public class Node {

    public Node next;
    public Node prev;
    private String t;

    public Node(Node next, Node prev, String token) {
        this.next = next;
        this.prev = prev;
        this.t = token;
    }

    public Node(String token) {
        this(null, null, token);
    }

    public String getString() {
        return t;
    }

    public void setString(String token) {
        this.t = token;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (!(other instanceof Node))
            return false;

        return t.equals(((Node) other).getString());
    }

    @Override
    public int hashCode() {
        if (t == null)
            return 0;
        return t.hashCode();
    }
}

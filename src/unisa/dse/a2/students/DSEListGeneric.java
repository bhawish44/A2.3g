package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

public class DSEListGeneric<T> implements ListGeneric<T> {

    public NodeGeneric<T> head;
    private NodeGeneric<T> tail;

    public DSEListGeneric() {
        head = null;
        tail = null;
    }

    public DSEListGeneric(NodeGeneric<T> head_) {
        this.head = head_;
        this.tail = head_;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
    }

    public DSEListGeneric(DSEListGeneric<T> other) {
        if (other == null || other.head == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = new NodeGeneric<>(null, null, other.head.getItem());
            NodeGeneric<T> currentOther = other.head.next;
            NodeGeneric<T> currentThis = this.head;

            while (currentOther != null) {
                NodeGeneric<T> newNode = new NodeGeneric<>(null, currentThis, currentOther.getItem());
                currentThis.next = newNode;
                currentThis = newNode;
                currentOther = currentOther.next;
            }
            this.tail = currentThis;
        }
    }

    public boolean add(T obj) {
        if (obj == null) return false;
        NodeGeneric<T> newNode = new NodeGeneric<>(null, tail, obj);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return true;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        NodeGeneric<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodeGeneric<T> current = head;
        while (current != null) {
            sb.append(current.getItem());
            if (current.next != null) sb.append(" ");
            current = current.next;
        }
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof DSEListGeneric<?>)) return false;
        DSEListGeneric<?> that = (DSEListGeneric<?>) other;
        NodeGeneric<T> currentThis = this.head;
        NodeGeneric<?> currentThat = that.head;
        while (currentThis != null && currentThat != null) {
            if (!currentThis.getItem().equals(currentThat.getItem())) {
                return false;
            }
            currentThis = currentThis.next;
            currentThat = currentThat.next;
        }
        return currentThis == null && currentThat == null;
    }

    public int indexOf(T obj) {
        int index = 0;
        NodeGeneric<T> current = head;
        while (current != null) {
            if (current.getItem().equals(obj)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0) return null;
        NodeGeneric<T> current = head;
        int count = 0;
        while (current != null) {
            if (count == index) return current.getItem();
            current = current.next;
            count++;
        }
        return null;
    }

    public boolean add(int index, T obj) {
        if (obj == null || index < 0) return false;
        if (index == 0) {
            NodeGeneric<T> newNode = new NodeGeneric<>(head, null, obj);
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
            return true;
        }
        NodeGeneric<T> current = head;
        int count = 0;
        while (current != null && count < index) {
            current = current.next;
            count++;
        }
        if (current == null) {
            return add(obj);
        } else {
            NodeGeneric<T> newNode = new NodeGeneric<>(current, current.prev, obj);
            if (current.prev != null) current.prev.next = newNode;
            current.prev = newNode;
            return true;
        }
    }

    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    public boolean remove(T obj) {
        NodeGeneric<T> current = head;
        while (current != null) {
            if (current.getItem().equals(obj)) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;

                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;

                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T remove(int index) {
        if (index < 0) return null;
        NodeGeneric<T> current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                T data = current.getItem();
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;

                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;

                return data;
            }
            current = current.next;
            count++;
        }
        return null;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

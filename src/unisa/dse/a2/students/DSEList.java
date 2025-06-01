package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

public class DSEList implements List {

    public Node head;
    private Node tail;

    public DSEList() {
        head = null;
        tail = null;
    }

    public DSEList(Node head_) {
        this.head = head_;
        this.tail = head_;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
    }

    public DSEList(DSEList other) {
        if (other == null || other.head == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = new Node(null, null, other.head.getString());
            Node currentOther = other.head.next;
            Node currentThis = this.head;

            while (currentOther != null) {
                Node newNode = new Node(null, currentThis, currentOther.getString());
                currentThis.next = newNode;
                currentThis = newNode;
                currentOther = currentOther.next;
            }
            this.tail = currentThis;
        }
    }

    public boolean add(String obj) {
        if (obj == null) return false;
        Node newNode = new Node(null, tail, obj);
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
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.getString());
            if (current.next != null) sb.append(" ");
            current = current.next;
        }
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof DSEList)) return false;
        DSEList that = (DSEList) other;
        Node currentThis = this.head;
        Node currentThat = that.head;
        while (currentThis != null && currentThat != null) {
            if (!currentThis.getString().equals(currentThat.getString())) {
                return false;
            }
            currentThis = currentThis.next;
            currentThat = currentThat.next;
        }
        return currentThis == null && currentThat == null;
    }

    public int indexOf(String obj) {
        int index = 0;
        Node current = head;
        while (current != null) {
            if (current.getString().equals(obj)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public String get(int index) {
        if (index < 0) return null;
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) return current.getString();
            current = current.next;
            count++;
        }
        return null;
    }

    public boolean add(int index, String obj) {
        if (obj == null || index < 0) return false;
        if (index == 0) {
            Node newNode = new Node(head, null, obj);
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
            return true;
        }
        Node current = head;
        int count = 0;
        while (current != null && count < index) {
            current = current.next;
            count++;
        }
        if (current == null) {
            return add(obj); // append to end if index is beyond bounds
        } else {
            Node newNode = new Node(current, current.prev, obj);
            if (current.prev != null) current.prev.next = newNode;
            current.prev = newNode;
            return true;
        }
    }

    public boolean contains(String obj) {
        return indexOf(obj) != -1;
    }

    public boolean remove(String obj) {
        Node current = head;
        while (current != null) {
            if (current.getString().equals(obj)) {
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

    public String remove(int index) {
        if (index < 0) return null;
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                String data = current.getString();
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
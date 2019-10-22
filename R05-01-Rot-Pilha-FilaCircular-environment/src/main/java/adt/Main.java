package adt;

import adt.queue.QueueImpl;
import adt.queue.QueueOverflowException;

public class Main {

    public static void main(String[] args) throws QueueOverflowException {
        QueueImpl qi = new QueueImpl(4);

        qi.enqueue(1);
        qi.enqueue(2);
        qi.enqueue(3);

        System.out.println(qi.indexOf(2));

    }
}

package com.chaochaogu.concurrencyinpractice.chapter15.atomicandnonblocking;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用Treiber算法（Treiber，1986）构造的非阻塞栈
 *
 * @author chaochao Gu
 * @date 2020/4/24
 */
@ThreadSafe
public class ConcurrentStack<E> {
    private final AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        }
        while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
            return oldHead.item;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}

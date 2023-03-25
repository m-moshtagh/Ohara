package com.dogigiri.designpatterns.mosh.memento.exercise;

import java.util.ArrayDeque;
import java.util.Deque;

public class History<E extends Memento> {
    private final Deque<E> states = new ArrayDeque<>();

    public void push(E state) {
        states.push(state);
    }

    public E pop() {
        return states.pop();
    }
}

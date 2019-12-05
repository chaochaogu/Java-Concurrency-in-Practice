package com.chaochaogu.concurrencyinpractice.chapter3;

/**
 * 隐式地使this引用逸出（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(new EvevtListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    private void doSomething(Event event) {
    }

    interface EvevtListener {
        void onEvent(Event e);
    }

    interface Event {
    }

    interface EventSource {
        void registerListener(EvevtListener listener);
    }
}

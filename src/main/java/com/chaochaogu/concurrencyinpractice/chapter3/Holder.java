package com.chaochaogu.concurrencyinpractice.chapter3;

/**
 * 由于未被正确发布，因此这个类可能出现故障
 *
 * @author chaochao gu
 * @date 2019/12/7
 */
public class Holder {

    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("this statement is false.");
        }
    }

    public static void main(String[] args) {
        Holder holder = new Holder(42);
        holder.assertSanity();
    }
}

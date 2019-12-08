package com.chaochaogu.concurrencyinpractice.chapter3.sharingobjects;

import javax.xml.ws.Holder;

/**
 * 在没有足够同步的情况下发布对象（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/7
 */
public class StuffIntoPublic {

    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}

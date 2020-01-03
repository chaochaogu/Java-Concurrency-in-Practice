package com.chaochaogu.concurrencyinpractice.chapter8.applyingthreadpools.puzzle;

import java.util.Set;

/**
 * 表示“搬箱子”之类谜题的抽象类
 *
 * @author chaochao gu
 * @date 2020/1/3
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}

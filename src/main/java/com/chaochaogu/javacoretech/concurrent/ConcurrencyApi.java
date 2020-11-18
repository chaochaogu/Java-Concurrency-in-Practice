package com.chaochaogu.javacoretech.concurrent;

import com.google.common.util.concurrent.Runnables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * 并发code api样例
 *
 * @author chaochao Gu
 * @date 2019/11/25
 */
@Slf4j
public class ConcurrencyApi {

    public static final ThreadLocal<SimpleDateFormat> DATE_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) throws Exception {
        DATE_FORMAT.get().format(new Date());
        int random = ThreadLocalRandom.current().nextInt(3);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("join" + i);
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("tjoin" + i);
            }
        });
        // 是否为守护线程
        t1.setDaemon(true);
        t1.start();
        try {
            // wait for this thread to die
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        t1.interrupt();
        t1.isInterrupted();
        Thread.interrupted();
        Thread.State t1State = t1.getState();
        Thread thread = Thread.currentThread();
        t1.setPriority(Thread.NORM_PRIORITY);
        // 让步
        Thread.yield();
        t1.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println("defaultUncaughtExceptionHandler" + Thread.getDefaultUncaughtExceptionHandler());
            System.out.println("exception" + e);
        });
        // 可重入锁
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
        // 条件对象
        Condition condition = lock.newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition.signal();
        condition.signalAll();
        try {
            // Object final method
            t1.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Object final method
        t1.notify();
        // Object final method
        t1.notifyAll();
        // 原子性
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.incrementAndGet();
        atomicLong.accumulateAndGet(9, (a, b) -> a + b);
        System.out.println(atomicLong.compareAndSet(10, 11));
        System.out.println(atomicLong);
        LongAccumulator adder = new LongAccumulator(Long::sum, 0);
        adder.accumulate(2);
        System.out.println(adder);
        if (lock.tryLock()) {
            // now the thread owns the lock
            try {
            } finally {
                lock.unlock();
            }
        } else {
            // do something else
        }
        // 读写锁
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        Lock readLock = rwl.readLock();
        Lock writeLock = rwl.writeLock();
        // 对所有的获取方法加读锁
        readLock.lock();
        readLock.unlock();
        // 对所有的修改方法加写锁
        writeLock.lock();
        writeLock.unlock();
        // 阻塞队列
        Queue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        Queue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10, true);
        Queue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Queue<Delayed> delayQueue = new DelayQueue<>();
        try {
            ((LinkedBlockingQueue<String>) linkedBlockingQueue).offer("1", 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrayBlockingQueue.peek();
        priorityBlockingQueue.poll();
        // 高效映射、集和队列
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>();
        ConcurrentSkipListMap<String, Object> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<String> concurrentSkipListSet = new ConcurrentSkipListSet<>();
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        // 映射条目的原子更新
        Long compute = concurrentHashMap.compute("word", (k, v) -> v == null ? 1 : v + 1);
        Long merge = concurrentHashMap.merge("word", 1L, Long::sum);
        ConcurrentHashMap<String, LongAdder> atomicMap = new ConcurrentHashMap<>();
        atomicMap.get("word").increment();
        // 对并发散列映射的批操作
        String search = concurrentHashMap.search(1L, (k, v) -> k.equals("123") ? k : null);
        concurrentHashMap.forEach(1L, (k, v) -> System.out.println("key" + k + "value" + v));
        Long reduceValues = concurrentHashMap.reduceValues(1L, Long::sum);
        // 并发集视图
        Set<String> keys = ConcurrentHashMap.newKeySet();
        // 写数组的拷贝
        new CopyOnWriteArrayList<String>();
        new CopyOnWriteArraySet<String>();
        // 并行数组算法
        Arrays.parallelSort(new String[]{"12", "235", "3"}, Comparator.comparing(String::length));
        // 同步包装器（synchronization wrapper）
        List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Map<Object, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        // Callable与Future
        Future<Integer> future = new Future<Integer>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Integer get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
        Callable<Integer> myComputation = () -> 1;
        FutureTask<Integer> task = new FutureTask<>(myComputation);
        // it's a Runnable
        new Thread(task).start();
        // it's a Future
        try {
            Integer res = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(3);
        Executors.newSingleThreadExecutor();
        Executors.newSingleThreadScheduledExecutor();
        Future<Integer> submit = executor.submit(myComputation);
        submit.cancel(true);
        submit.isDone();
        executor.shutdownNow();
        // 预定执行
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(() -> System.out.println("3"), 3L, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("1"), 3L, 4L, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        // 控制任务组
        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(myComputation);
        try {
            List<Future<Integer>> results = executor.invokeAll(tasks);
            for (Future<Integer> result : results) {
                result.get();
                // do business
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<>(executor);
        for (Callable task1 : tasks) {
            executorCompletionService.submit(task1);
        }
        for (int i = 0; i < tasks.size(); i++) {
            try {
                executorCompletionService.take().get();
                // do business
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Fork-Join框架(RecursiveTask)，具体代码见daily项目{@link com.chaochaogu.daily.fork.join.Client}
        // 可完成 Future(CompletableFuture)
        ExecutorService fixTmallFeeService = new ThreadPoolExecutor(16, 20, 60L, MINUTES,
                new LinkedBlockingQueue<>(500000),
                new ThreadFactoryBuilder().setNameFormat("tmall-presale-fee-executor-%d").build(),
                (r, exec) -> log.error("fixTmallFee task {} is rejected", r));
        CompletableFuture.runAsync(Runnables.doNothing(), fixTmallFeeService);

        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> new Random().nextInt(10)).thenAccept(System.out::println);
        future1.get();

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return new Random().nextInt(10);
        }).handle((param, throwable) -> {
            int result = -1;
            if (throwable == null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }
            return result;
        });
        System.out.println(future2.get());
    }
}

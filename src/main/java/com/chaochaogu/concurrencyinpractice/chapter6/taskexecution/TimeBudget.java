package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 在预定时间内请求旅游报价
 *
 * @author chaochao Gu
 * @date 2019/12/27
 */
public class TimeBudget {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo,
                                                   Set<TravelCompany> companies,
                                                   Comparator<TravelQuote> ranking,
                                                   long time,
                                                   TimeUnit unit) throws InterruptedException {
        List<QuoteTask> tasks = Lists.newArrayList();
        companies.forEach((company) -> tasks.add(new QuoteTask(company, travelInfo)));
        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);
        List<TravelQuote> quotes = Lists.newArrayListWithCapacity(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIter.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }
        quotes.sort(ranking);
        return quotes;
    }

    class QuoteTask implements Callable<TravelQuote> {

        private final TravelCompany company;
        private final TravelInfo travelInfo;

        public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
            this.company = company;
            this.travelInfo = travelInfo;
        }

        TravelQuote getFailureQuote(Throwable t) {
            return null;
        }

        TravelQuote getTimeoutQuote(CancellationException e) {
            return null;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(travelInfo);
        }
    }

    interface TravelCompany {
        TravelQuote solicitQuote(TravelInfo travelInfo) throws Exception;
    }

    interface TravelQuote {
    }

    interface TravelInfo {
    }
}

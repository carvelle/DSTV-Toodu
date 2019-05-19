package za.co.dstv.tooduapp.utils;

import io.reactivex.Scheduler;

public interface CustomScheduler {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}

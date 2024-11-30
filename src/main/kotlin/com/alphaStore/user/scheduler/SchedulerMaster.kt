package com.alphaStore.merchant.scheduler

import org.springframework.scheduling.annotation.Scheduled

class SchedulerMaster {
    @Scheduled(cron = "*/10 * * * * *")
    fun runsEvery10Seconds() {
        println("Running every 10 seconds...")
    }

    // Example method to run every 5 minutes
    @Scheduled(cron = "0 */5 * * * *")
    fun runsEvery5Minutes() {
        println("Running every 5 minutes...")
    }

    // Example method to run every 2 hours
    @Scheduled(cron = "0 0 */2 * * *")
    fun runsEvery2Hours() {
        println("Running every 2 hours...")
    }

    // Example method to run every midnight (12:00 AM)
    @Scheduled(cron = "0 0 0 * * *")
    fun runsAtMidnight() {
        println("Running at midnight...")
    }
}
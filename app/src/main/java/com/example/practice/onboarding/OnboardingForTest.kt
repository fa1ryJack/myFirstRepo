package com.example.practice.onboarding

import java.util.*

class OnboardingForTest {
    private val queue: Queue<OnboardingItem> = LinkedList()

    fun addItem(item: OnboardingItem) {
        queue.offer(item)
    }

    fun getNextItem(): OnboardingItem? {
        return queue.poll()
    }

    fun getSize(): Int {
        return queue.size
    }
}
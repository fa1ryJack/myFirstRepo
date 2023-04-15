package com.example.practice

import com.example.practice.onboarding.OnboardingItem
import com.example.practice.onboarding.OnboardingForTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class OnboardTest1 {
    private lateinit var onboardingQueue: OnboardingForTest

    @Before
    fun setUp() {
        onboardingQueue = OnboardingForTest()
    }

    @Test
    fun shouldRetrieveItemsInOrder() {
        // Создаем три примера OnboardingItem
        val item1 = OnboardingItem("Image1", "Text1", 1)
        val item2 = OnboardingItem("Image2", "Text2", 2)
        val item3 = OnboardingItem("Image3", "Text3", 3)

        // Добавляем элементы в очередь
        onboardingQueue.addItem(item1)
        onboardingQueue.addItem(item2)
        onboardingQueue.addItem(item3)

        // Извлекаем элементы из очереди и проверяем порядок
        assertEquals(item1, onboardingQueue.getNextItem())
        assertEquals(item2, onboardingQueue.getNextItem())
        assertEquals(item3, onboardingQueue.getNextItem())
    }

    @Test
    fun shouldDecreaseQueueSizeByOneWhenItemIsRetrieved() {
        // Создаем три примера OnboardingItem
        val item1 = OnboardingItem("Image1", "Text1", 1)
        val item2 = OnboardingItem("Image2", "Text2", 2)
        val item3 = OnboardingItem("Image3", "Text3", 3)

        // Добавляем элементы в очередь
        onboardingQueue.addItem(item1)
        onboardingQueue.addItem(item2)
        onboardingQueue.addItem(item3)

        // Проверяем начальный размер очереди
        assertEquals(3, onboardingQueue.getSize())
        // Проверяем начальный размер очереди
        assertEquals(3, onboardingQueue.getSize())

        // Извлекаем элементы из очереди и проверяем размер после каждого извлечения
        onboardingQueue.getNextItem()
        assertEquals(2, onboardingQueue.getSize())

        onboardingQueue.getNextItem()
        assertEquals(1, onboardingQueue.getSize())

        onboardingQueue.getNextItem()
        assertEquals(0, onboardingQueue.getSize())

        // Проверяем, что извлечение элемента из пустой очереди не вызывает ошибку и возвращает null
        assertNull(onboardingQueue.getNextItem())
    }
}
package com.pswiderski.kotlin

import java.util.concurrent.atomic.AtomicLong

val counter = AtomicLong()

data class Person(val id: Long = counter.incrementAndGet(), val name: String, val age: Int)
# Ex01 - Implementing thread-safe singleton

---

A singleton is an object that has only a single instance throughout the entire application's lifespan.

Implement the getInstance() methods in each of the classes and do any necessary modifications if need be to make the class thread-safe and let the unit tests pass :

- [A_EagerInitSingleton](A_EagerInitSingleton.java)
- [B_LazySingleton.java](B_LazySingleton.java)****
- [C_LazyDoubleCheckedSingleton.java](C_LazyDoubleCheckedSingleton.java)
- [D_StaticHolderSingleton.java](D_StaticHolderSingleton.java)
- [E_EnumSingleton.java](E_EnumSingleton.java)

## Ex02 - Multithreaded Prime Checked

---
Given a list of numbers, write a multithreaded program that checks if each number is prime or not.

- Using Threads
- Using parallel streams
- Using ExecutorService
- Using FJ Pool

---

## Ex03 - The Dining Philosophers Problem

---
https://en.wikipedia.org/wiki/Dining_philosophers_problem

- Don't use `synchronized`

## Ex03b - Publish/Subscribe

## Ex04 - Tools demonstration

---

## Ex05 : Detect and Fix a Deadlock/Race Condition

---

- Review Exercise 03

## Kata - Cake machine kata

---

1. Create a Cake class with a unique id for every new instance
    1. do it without synchronization
2. Create a "Step" class that
    1. accepts a cake as input
    2. does the work (preparing, baking or packaging)
    3. limits the task that can be executed in parallel
    4. once work is done, passes the result
3. Create a Machine class that handles the step
    1. submits cakes thru the steps


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
Five philosophers dine together at the same table. Each philosopher has his own plate at the table. There is a fork between each plate. The dish served is a kind of spaghetti which has to be eaten with two forks. Each philosopher can only alternately think and eat. Moreover, a philosopher can only eat his spaghetti when he has both a left and right fork. Thus two forks will only be available when his two nearest neighbors are thinking, not eating. After an individual philosopher finishes eating, he will put down both forks. The problem is how to design a regimen (a concurrent algorithm) such that any philosopher will not starve; i.e., each can forever continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.
https://en.wikipedia.org/wiki/Dining_philosophers_problem

- Don't use `synchronized` in you solution

## Ex03b - Publish/Subscribe

- Implement a pub/sub system, where 1 thread is the publisher and at least 2 threads are subscribers. The publisher will send messages to the subscribers. The subscribers will print the messages they receive.

## Ex04 - Tools demonstration

---

## Ex05 : Detect and Fix a Deadlock/Race Condition

- Perform a thread dump using `jstack` or `jvisualvm` and analyze the output to detect a deadlock.

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


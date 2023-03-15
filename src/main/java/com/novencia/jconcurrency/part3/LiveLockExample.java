package com.novencia.jconcurrency.part3;

import static com.novencia.jconcurrency.Util.handleInterruptedException;

public class LiveLockExample {
    public static void main(String[] args) {
        var husband = new Diner("Husband");
        var wife = new Diner("Wife");

        var sharedSpoon = new Spoon(husband);

        var husbandThread = new Thread(() -> husband.eatWith(sharedSpoon, wife));
        var wifeThread = new Thread(() -> wife.eatWith(sharedSpoon, husband));

        husbandThread.start();
        wifeThread.start();
    }

    static class Spoon {
        private Diner owner;

        public Spoon(Diner d) {
            owner = d;
        }

        public synchronized void use() {
            System.out.println(owner.name + " has the spoon!");
        }

        public synchronized void setOwner(Diner d) {
            owner = d;
        }
    }

    static class Diner {
        private final String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // Try to pick up the spoon, but if spouse owns it, relinquish it
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1000); // Simulate some work
                    } catch (InterruptedException e) {
                        handleInterruptedException(e);
                    }
                    continue;
                }

                // Check if spouse is hungry, and if so, switch the spoon's owner
                if (spouse.isHungry) {
                    System.out.println(name + " sees that " + spouse.name + " is hungry, so gives the spoon.");
                    spoon.setOwner(spouse);
                    continue;
                }

                // Eating
                spoon.use();
                isHungry = false;
                System.out.println(name + " finished eating.");
                spoon.setOwner(spouse);

            }
        }
    }
}

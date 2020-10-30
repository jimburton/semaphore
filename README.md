# Counting semaphores

A *semaphore* uses state (such as a boolean variable) to allow threads to communicate with each other,
e.g. to indicate that a resource has become available, or to guard a critical section in the same
way that a `Lock` does. A *binary semaphore* uses a boolean variable to indicate true/false,
available/unavailable etc. A *counting semaphore* uses an integer, *n*, to allow *n* threads to 
access the resource or critical section.

Consider a scenario in which a librarian manages access to *n* desks in a library. Students enter the
library, approach the librarian and ask to use a desk. If a desk is available, the librarian allows
the student to use it. If all the desks are busy, the student waits. When a student completes their
work, they leave and the librarian notifies one of the waiting students that they can use the desk
which is now free.

Implement this scenario in the package `CI646.semaphore`. The `Librarian` class should contain two 
fields, `numDesks` (the total number of desks in the library) and `numStudents` (the number of 
students currently at work in the library). The constructor should take a long parameter, which
is the number of desks. The class should include methods with these signatures:

+ `public synchronized void requestDesk() throws InterruptedException` 
+ `public synchronized void releaseDesk() throws InterruptedException`

The `requestDesk` method should call `wait` if all of the desks are busy, then increment the number
of students at work and call `notify` to wake up the waiting students.

The `releaseDesk` method should call `wait` if no students are working, then decrement the number
of students at work and call `notify` to wake up the waiting students.

The `Student` class needs two fields, an instance of `Librarian` and a `long` representing
the amount of work the student will be doing. It also needs a constructor which accepts and sets 
the `Librarian` instance and `long`. Since neither field will be changed after it is first set, mark
them as `final`. Since `Student` is a subclass of `Thread` it needs a `run` method. This method
should ask the librarian for a desk, then sleep for the right amount of time (typical!), then let
the librarian know that the desk is free again. Use the logger defined in the `Main` class to log
what the student is doing. For example,

    Main.LOG.info(String.format("Student %s would like to work", getName()));
    librarian.requestDesk();
    //etc

Run the `main` method in `CI646.Main` to create a librarian in charge of 3 desks, and 10 students
who need to use them.

What changes would you make to this application if you were to use Java's `ExecutorService` instead of our home-grown semaphore? What are the advantages and disadvantages of doing so?

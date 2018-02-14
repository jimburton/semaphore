package CI346.semaphore;

import CI346.Main;

public class Student extends Thread {

    private final Librarian librarian;
    private final long work;

    public Student(Librarian librarian, long work) {
        this.librarian = librarian;
        this.work = work;
    }

    @Override
    public void run() {
        try {
            Main.LOG.info(String.format("Student %s would like to work", getName()));
            librarian.requestDesk();
            Main.LOG.info(String.format("Student %s WORKING", getName()));
            sleep(work);
            librarian.releaseDesk();
            Main.LOG.info(String.format("Student %s FINISHED", getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

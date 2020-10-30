package CI646.semaphore;

public class Librarian {

    private final int numDesks;
    private int studentsWorking = 0;

    public Librarian(int numDesks) {
        this.numDesks = numDesks;
    }

    public synchronized void requestDesk() throws InterruptedException {
        while(numDesks == studentsWorking) wait();
        studentsWorking++;
        notify();
    }

    public synchronized void releaseDesk() throws InterruptedException {
        while(studentsWorking == 0) wait();
        studentsWorking--;
        notify();
    }
}

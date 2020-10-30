package CI646;

import CI646.semaphore.Librarian;
import CI646.semaphore.Student;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Main {

    public static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Librarian lib = new Librarian(3);
        int numStudents = 10;

        for(int i=0; i<numStudents;i++) {
            int work = ThreadLocalRandom.current().nextInt(3000, 10000);
            new Student(lib, work).start();
        }
    }
}

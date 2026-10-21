import java.io.File;

public class Main {
    public static void main(String[] args) {
        RegistryManager manager = new RegistryManager();

        // Change this to the absolute path of YOUR students.txt
        File file = new File("C:\\Users\\YourName\\Documents\\students.txt");

        manager.loadStudentsFromFile(file.getPath());
        manager.displayAllStudents();
        manager.groupStudentsByMajor();
    }
}
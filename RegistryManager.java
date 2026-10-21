import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RegistryManager {
    private List<Student> students = new ArrayList<>();

    public void loadStudentsFromFile(String fileName) {
        students.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                String[] parts = line.split(",");
                if (parts.length != 4) {
                    continue; // skip lines that are not valid records (e.g. the header)
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double gpa = Double.parseDouble(parts[2].trim());
                    String major = parts[3].trim();

                    students.add(new Student(id, name, gpa, major));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Loaded " + students.size() + " students from file.\n");
        } catch (IOException e) {
            System.out.println("Error reading file '" + fileName + "': " + e.getMessage());
        }
    }

    public void displayAllStudents() {
        System.out.println("=== All Students ===");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println();
    }

    public void groupStudentsByMajor() {
        Map<String, List<String>> groups = new LinkedHashMap<>();

        for (Student s : students) {
            groups.computeIfAbsent(s.getMajor(), k -> new ArrayList<>()).add(s.getName());
        }

        System.out.println("=== Students Grouped by Major ===");
        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
        System.out.println();
    }
}
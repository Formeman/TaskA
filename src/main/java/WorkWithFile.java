import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkWithFile {
    public static void main(String[] args) throws IOException {
        System.out.println(getEmail());
        System.out.println(getPassword());
        System.out.println(work());
}

    public static String write(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();
        String emails = "";
        while (line != null) {
            if (line.contains("Login data"))
                emails+=line.split(":")[1];
            line = reader.readLine();
        }
        return emails;
    }

    public static String work() throws IOException {
        String fileWay = "Emails";
        File dir = new File(fileWay);
        String emails = "";
        for (File item : Objects.requireNonNull(dir.listFiles()))
            emails += write(item);
        return emails;
    }

    public static String getEmail() throws IOException {
        FileReader fr = new FileReader("LoginPass.txt");
        BufferedReader reader = new BufferedReader(fr);

        return reader.readLine().split("@")[0];
    }

    public static String getPassword() throws IOException {
        FileReader fr = new FileReader("LoginPass.txt");
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();
        line = reader.readLine();
        return line;
    }

    public static String getMessage() throws IOException{
        String message = "";
        FileReader fr = new FileReader("Message.txt");
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();
        while (line != null) {
            message+=line;
            message+="\n";
            line = reader.readLine();
        }
        return message;
    }

    public static String getSubject() throws IOException{
        String message = "";
        FileReader fr = new FileReader("Subject.txt");
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();
        while (line != null) {
            message+=line;
            message+="\n";
            line = reader.readLine();
        }
        return message;
    }
}

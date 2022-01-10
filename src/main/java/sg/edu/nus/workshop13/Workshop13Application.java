package sg.edu.nus.workshop13;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop13Application {
	public static void main(String[] args) {
		SpringApplication.run(Workshop13Application.class, args);
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        if (appArgs.containsOption("dataDir")) {
            List argsString = appArgs.getOptionValues("dataDir");
            String dirPathname = argsString.get(0).toString();
            if (dirPathname.matches("[/]*")) {
                dirPathname = "." + dirPathname;
            }
            System.out.println(dirPathname);
            Path directoryPath = Paths.get(dirPathname);
            File dirFile = directoryPath.toFile();
            if (!(dirFile.exists() || dirFile.isDirectory() || (!dirFile.canRead()))) {
                File createNewFile = new File(dirPathname);
                createNewFile.mkdirs();
                System.out.println("File created " + dirPathname);
            } else {
                System.out.println("File " + dirPathname + " found");
            }
        } else {
            System.out.println("You must start the program with parameter --dataDir");
            System.exit(1);
        }
	}

}

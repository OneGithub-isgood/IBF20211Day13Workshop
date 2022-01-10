package sg.edu.nus.workshop13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
    private static final Logger logger = LoggerFactory.getLogger(Helper.class);
    
    private String adjustedFileDirectory = "";
    private List<String> readDataList = new ArrayList<>();;

    public void writeToFile(String fileDirectory, String fileName, List<String> writeDataList) {
        if (fileDirectory.matches("^\\\\.*")) { //To catch 1 \ in Regex string, need 4 \...
            adjustedFileDirectory = "." + fileDirectory + "\\" + fileName;
        } else {
            adjustedFileDirectory = fileDirectory + "\\" + fileName;
        }
        Path dataFilePath = Paths.get(adjustedFileDirectory);
        try {
            Path newDataFilePath = Files.createFile(dataFilePath);
            File newDataFile = newDataFilePath.toFile();
            BufferedWriter newBW = new BufferedWriter(new FileWriter(newDataFile, true));
            try {
                for (String writeData : writeDataList) {
                    newBW.write(writeData);
                    logger.info("Write Line > " + writeData);
                    newBW.newLine();
                }
            } finally {
                newBW.close();
            }
        } catch (IOException eIO) {
            eIO.printStackTrace();
        }
    }

    public List<String> readFromFile(String fileDirectory, String fileName) {
        if (fileDirectory.matches("^\\\\.*")) { //To catch 1 \ in Regex string, need 4 \...
            adjustedFileDirectory = "." + fileDirectory + "\\" + fileName;
            logger.info("Pass Condition > " + adjustedFileDirectory);
        } else {
            adjustedFileDirectory = fileDirectory + "\\" + fileName;
            logger.info("Fail Condition > " + adjustedFileDirectory);
        }
        String readLine;
        File dbFile = new File(adjustedFileDirectory);
        logger.info("Read Filepath > " + adjustedFileDirectory);
        try (Reader newReader = new FileReader(dbFile)) {
            BufferedReader newBR = new BufferedReader(newReader);
            while (null != (readLine = newBR.readLine())) {
                readDataList.add(readLine);
                logger.info("Write Line > " + readLine);
            }
        } catch (FileNotFoundException ExFNF) { } catch (IOException ExIO) {}
        return readDataList;
    }

}
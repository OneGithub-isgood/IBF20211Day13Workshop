package sg.edu.nus.workshop13;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import sg.edu.nus.workshop13.model.Contact;

public class Contacts {
    private static final Logger logger = LoggerFactory.getLogger(Contacts.class);

    private String dirPath = "";
    private List<String> writeFileData = new ArrayList<String>();

    public void saveContact(Contact contact, Model model, ApplicationArguments appArgs) {
        Helper helpWriteFiles = new Helper();
        dirPath = appArgs.getOptionValues("dataDir").get(0);
        writeFileData.add(contact.getName());
        writeFileData.add(contact.getEmail());
        writeFileData.add(Integer.toString(contact.getPhoneNum())); //BufferedWriter can only write String!
        helpWriteFiles.writeToFile(dirPath, contact.getUserID(), writeFileData);
        logger.info("Save User ID > " + contact.getUserID());
        model.addAttribute("contact", new Contact(contact.getUserID(), contact.getName(), contact.getEmail(), contact.getPhoneNum()));
    }

    public void viewContact(Model model, String userID, ApplicationArguments appArgs) {
        Helper helpReadFiles = new Helper();
        dirPath = appArgs.getOptionValues("dataDir").get(0);
        Contact existingContact = new Contact(userID);
        logger.info("View User ID > " + existingContact.getUserID());
        List<String> readFileData = helpReadFiles.readFromFile(dirPath, userID);
        existingContact.setName(readFileData.get(0).toString());
        existingContact.setEmail(readFileData.get(1).toString());
        existingContact.setPhoneNum(Integer.parseInt(readFileData.get(2)));
        model.addAttribute("contact", existingContact);
    }
}

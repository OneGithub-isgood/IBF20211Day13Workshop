package sg.edu.nus.workshop13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.nus.workshop13.model.Contact;
import sg.edu.nus.workshop13.Contacts;

@Controller
@RequestMapping(value = {"/", "/contact"})
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ApplicationArguments appArgs;
    
    /*
    @GetMapping(produces = {"text/html"})
    public String redirectToContactPage() {
        return "contact";
    }
    */

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact";
    }

    @GetMapping("/contact/{userId}")
    public String viewAddressBookByID(Model model, @PathVariable(value="userId") String userID) {
        Contacts contacts = new Contacts();
        contacts.viewContact(model, userID, appArgs);
        return "contactWithID";
    }

    @PostMapping("/contact")
    public String saveAddressBook(@ModelAttribute Contact contact, Model model) {
        logger.info("Phone Number > " + contact.getPhoneNum());
        logger.info("User ID > " + contact.getUserID());
        Contacts contacts = new Contacts();
        contacts.saveContact(contact, model, appArgs);
        return "contactWithID";
    }
}

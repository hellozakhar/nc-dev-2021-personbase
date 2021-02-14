package com.hellozakhar.controller;

import com.hellozakhar.model.MailObject;
import com.hellozakhar.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MailController {

    @Autowired
    public EmailService emailService;

    @GetMapping(value = "/sendmail/{toemail}")
    public String mailSender(@PathVariable(name="toemail", required = true) String toemail, Model model) {
        model.addAttribute("mailObject", new MailObject());
        return "sendmail";
    }

    @PostMapping("/sendmail")
    public String mailSenderResult(@ModelAttribute MailObject mailObject) {

        emailService.sendSimpleMessage(mailObject.getTo(),
                                       mailObject.getSubject(),
                                       mailObject.getText());

        return "sendmailresult";
    }
}

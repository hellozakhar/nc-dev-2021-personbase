package com.hellozakhar.controller;

import com.hellozakhar.util.CSVHandler;
import com.hellozakhar.model.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;

@Controller
public class PersonController {

    @Deprecated
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/addperson")
    public String addperson(Model model) {
        model.addAttribute("person", new Person());
        return "addperson";
    }

    @PostMapping("/addperson")
    public String addPersonResult(@ModelAttribute Person person) {
        String[] data = { person.getSurname(), person.getName(), person.getMiddlename(),
                          String.valueOf(person.getAge()), String.valueOf(person.getSalary()),
                          person.getEmail(), person.getCompany() };
        String CSV_FILE_PATH = "./data.csv";
        File file = new File(CSV_FILE_PATH);
        // if file exists, then prefer append data to the end
        // otherwise, create a new file with data
        boolean isAppendMode = file.exists();
        CSVHandler.writeToCSV(isAppendMode, data);

        return "addresult";
    }

    @GetMapping("/findperson")
    public String findperson(Model model) {
        model.addAttribute("person", new Person());
        return "findperson";
    }

    @PostMapping("/findperson")
    public String findPersonResult(@ModelAttribute Person person) {
        return (CSVHandler.findPersonInCSV(person)) != null ? "person-found" : "person-not-found";
    }
}
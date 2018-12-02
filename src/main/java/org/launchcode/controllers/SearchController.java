package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.capitalize;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
//    @ResponseBody
    public String results(HttpServletRequest request, Model model){
        model.addAttribute("columns", ListController.columnChoices);


        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");

        model.addAttribute("test", capitalize(searchType));

        if (searchType.equals("all")){
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            int count = jobs.size();
            String count1 = String.valueOf(count);
            model.addAttribute("count", count1 + " Result(s)");
            model.addAttribute("jobs", jobs);
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            int count = jobs.size();
            String count1 = String.valueOf(count);
            model.addAttribute("count", count1 + " Result(s)");
            model.addAttribute("jobs", jobs);

        }

        return "search";
    }



}

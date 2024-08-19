package com.example.demo.controller;

import com.example.demo.dto.ResPaging;
import com.example.demo.dto.request.user.ReqEmpApproval;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.user.CustomerSearchDTO;
import com.example.demo.dto.response.user.ResCustomerManageList;
import com.example.demo.enums.Role;
import com.example.demo.service.CustomerManageService;
import com.example.demo.user.EmpDecisionSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/customer/manage")
@RequiredArgsConstructor
public class CustomerManageController {

    private final CustomerManageService customerManageService;
    private final Map<String , EmpDecisionSignup> empDecisionSignup;

    @GetMapping("")
    public String customerManageList(Model model, @ModelAttribute("search") CustomerSearchDTO dto){
        ResPaging<ResCustomerManageList> customerManageList = customerManageService.customerManageList(dto);
        model.addAttribute("customerManageList",customerManageList);
        model.addAttribute("role", Role.values());
        return "customer_manage_list";
    }

    @PutMapping("/emp/decide")
    @ResponseBody
    public ResponseEntity<ResponseDTO> decideEmpSignup(ReqEmpApproval req){
        EmpDecisionSignup empDecision = empDecisionSignup.get(req.getDecisionMessage());
        ResponseDTO response = empDecision.empDecisionSignup(req.getUserSid());
        return ResponseEntity.ok(response);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.ResPaging;
import com.example.demo.dto.request.user.ReqEmpApproval;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.user.CustomerSearchDTO;
import com.example.demo.dto.response.user.ResCustomerManageList;
import com.example.demo.enums.Role;
import com.example.demo.service.CustomerManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer/manage")
@RequiredArgsConstructor
public class CustomerManageController {

    private final CustomerManageService customerManageService;

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
        ResponseDTO response = customerManageService.decideEmpSignup(req);
        return ResponseEntity.ok(response);
    }
}

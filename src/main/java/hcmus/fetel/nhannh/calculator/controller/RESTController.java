package hcmus.fetel.nhannh.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hcmus.fetel.nhannh.calculator.dto.RequestDTO;
import hcmus.fetel.nhannh.calculator.dto.ResponseDTO;
import hcmus.fetel.nhannh.calculator.service.CalculatorService;

@RestController
public class RESTController {
    
    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    @ResponseBody
    public List<ResponseDTO> calculator(@RequestBody RequestDTO request) {
        List<ResponseDTO> calculator = calculatorService.calculator(request);
        return calculator;
    }
}

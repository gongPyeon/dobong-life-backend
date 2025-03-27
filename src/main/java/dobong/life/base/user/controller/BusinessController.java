package dobong.life.base.user.controller;

import dobong.life.base.user.controller.request.BusinessReqDTO;
import dobong.life.base.user.controller.request.BusinessStoreReqDTO;
import dobong.life.base.user.service.BusinessService;
import dobong.life.global.util.response.BaseResponse;
import dobong.life.global.util.response.status.BaseCode;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
//@RequiredArgsConstructor
//@RequestMapping("dobong/business")
//@Slf4j
//public class BusinessController {
//    private final BusinessService businessService;
//
//
//    @PostMapping
//    public BaseResponse<BaseCode> checkBusiness(@Valid @RequestBody BusinessReqDTO businessDTO){
//        BaseCode baseCode = businessService.checkBusiness(businessDTO);
//        return new BaseResponse<>(baseCode);
//    }
//
//    @PostMapping("/store")
//    public BaseResponse<BaseCode> registerBusiness(@Valid @RequestBody BusinessStoreReqDTO businessStoreReqDTO){
//        BaseCode baseCode = businessService.registerBusiness(businessStoreReqDTO);
//        return new BaseResponse<>(baseCode);
//    }
//}

package dobong.life.base.user.service;

import dobong.life.base.user.controller.request.BusinessReqDTO;
import dobong.life.base.user.controller.request.BusinessStoreReqDTO;
import dobong.life.global.util.response.status.BaseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessService {
    public BaseCode checkBusiness(BusinessReqDTO businessDTO) {
        String name = businessDTO.getName();
        String number = businessDTO.getNumber();

        // TODO: open api로 사업장 확인을 한 후 (함수 확인)
        return BaseCode.SUCCESS_BUSINESS;
    }

    public BaseCode registerBusiness(BusinessStoreReqDTO businessStoreReqDTO) {

        // TODO: 관리자가 확인한 후 등록해준다 (HOW?)
        return BaseCode.SUCCESS_BUSINESS_STORE;
    }
}

package com.weart.csrs.service;

import com.weart.csrs.Repository.ReliabilityRepository;
import com.weart.csrs.domain.MEMBER.MEMBER;
import com.weart.csrs.domain.reliability.Reliability;
import com.weart.csrs.web.dto.ReliabilityResponseDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Getter
@Service
public class ReliabilityService {

    private static final String NOT_FOUND_MEMBER_MESSAGE = "ERR_MESSAGE";

    private final ReliabilityRepository reliabilityRepository;

     MEMBER member;

    @Autowired
    public ReliabilityService(ReliabilityRepository reliabilityRepository) {
        this.reliabilityRepository = reliabilityRepository;
    }

    @Transactional
    public boolean FlagGenerator(SuccessfulBidResponse successfulBidResponse){
        LocalDateTime current_Time = LocalDateTime.now();
        if(current_Time == successfulBidResponse.getDeadLine()){
            if(successfulBidResponse.getPurchaseFlag() == false){
                return true;
            }
        }
        return false;
    }

    //deadFlag 라인에 FlagGenerator return 값 대입하면 됨.
    @Transactional
    public void minusReliability(ReliabilityResponseDto reliabilityResponseDto, boolean deadFlag) {
        if (deadFlag) {
            Reliability currentReliability = reliabilityRepository.save(Reliability.builder()
                    .successfulBid(reliabilityResponseDto.getSuccessfulBid())
                    .warningScore(reliabilityResponseDto.getWarningScore() - 1).build());
        }
    }

//    신뢰도가 -2가 된다면 Role GUEST로 변환
//    백엔드 내에서 처리하기 때문에 Dto를 사용하지않고 거래정지
    @Transactional
    public void Transaction_Suspension(ReliabilityResponseDto reliabilityResponseDto){
        if(reliabilityResponseDto.getWarningScore() == -2){
            member.setRole(Role.GUEST);
        }
    }

}

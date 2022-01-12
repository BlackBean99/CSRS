package com.weart.csrs.config;

import com.weart.csrs.Repository.MEMBERRepository;
import com.weart.csrs.domain.reliability.ReliabilityRepository;
import com.weart.csrs.service.MEMBERService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MEMBERRepository memberRepository;
    public SpringConfig(MEMBERRepository memberRepository, ReliabilityRepository reliabilityRepository) {
        this.memberRepository = memberRepository;
        this.reliabilityRepository = reliabilityRepository;
    }
    private final ReliabilityRepository reliabilityRepository;

    @Bean
    public MEMBERService memberService() {
        return new MEMBERService(memberRepository);
    }
}

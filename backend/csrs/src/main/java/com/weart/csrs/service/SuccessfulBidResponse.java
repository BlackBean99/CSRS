package com.weart.csrs.service;

import com.weart.csrs.domain.bid.Bid;
import com.weart.csrs.domain.successfulbid.SuccessfulBid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SuccessfulBidResponse {
    private Bid bid;
    private LocalDateTime deadLine;
    // 0이면 구매하지 않음, 1이면 구매 O
    private Boolean purchaseFlag;


    public SuccessfulBidResponse(SuccessfulBid successfulBid) {
        this.bid = successfulBid.getBid();
        this.deadLine = successfulBid.getDeadline();
        this.purchaseFlag = successfulBid.getPurchaseFlag();
    }
}

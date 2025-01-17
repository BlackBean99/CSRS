package com.weart.csrs.domain.successfulbid;


import com.weart.csrs.domain.BaseTimeEntity;
import com.weart.csrs.domain.art.Art;
import com.weart.csrs.domain.bid.Bid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class SuccessfulBid extends BaseTimeEntity {
    private static final Long PURCHASE_LEFT_DAY = 7L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUCCESSFUL_BID_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "BID_ID")
    private Bid bid;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private Boolean purchaseFlag;

    public SuccessfulBid(Bid bid, LocalDateTime deadline, Boolean purchaseFlag) {
        this.bid = bid;
        this.deadline = deadline;
        this.purchaseFlag = purchaseFlag;
    }
}


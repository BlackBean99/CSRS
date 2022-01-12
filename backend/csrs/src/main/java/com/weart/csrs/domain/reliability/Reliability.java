package com.weart.csrs.domain.reliability;

import com.weart.csrs.domain.successfulbid.SuccessfulBid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reliability {
    @Id
    @Column(name = "RELIABILITY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "SUCCESSFUL_BID_ID")
    private SuccessfulBid successfulBid;

    @Column(nullable = false)
    private Long warningScore;

    @Builder
    public Reliability(Long iad, SuccessfulBid successfulBid, Long warningScore) {
        this.id = iad;
        this.successfulBid = successfulBid;
        this.warningScore = warningScore;
    }
}

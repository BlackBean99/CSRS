package com.weart.csrs.domain.credit;

import com.weart.csrs.domain.successfulbid.SuccessfulBid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREDIT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "SUCCESSFUL_BID_ID")
    private SuccessfulBid successfulBid;

    @Column(nullable = false)
    private Long balance;
}
package com.weart.csrs.domain.successfulbid;

import com.weart.csrs.domain.bid.Bid;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
=======
import javax.persistence.*;
>>>>>>> ca61c2c806415a3211e9249821de784c5bc39a7f
import java.time.LocalDateTime;

@Entity
public class SuccessfulBid {
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
}
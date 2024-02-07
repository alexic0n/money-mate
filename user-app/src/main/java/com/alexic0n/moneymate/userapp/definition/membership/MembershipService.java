package com.alexic0n.moneymate.userapp.definition.membership;

import com.alexic0n.moneymate.userapp.definition.AbstractUserApplicationService;
import com.alexic0n.moneymate.userapp.definition.membership.model.Membership;
import org.springframework.stereotype.Service;

@Service
public class MembershipService extends AbstractUserApplicationService<MembershipRepository, Membership> {
    public MembershipService(MembershipRepository repository) {
        super(repository, "membership");
    }
}

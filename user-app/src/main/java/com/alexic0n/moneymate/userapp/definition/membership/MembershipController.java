package com.alexic0n.moneymate.userapp.definition.membership;

import com.alexic0n.moneymate.userapp.definition.membership.model.Membership;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public ResponseEntity<Void> createMembership(@RequestBody @Valid Membership membership){
        return ResponseEntity.created(
                URI.create(format("/money-mate/membership-app/memberships/%s", membershipService.createEntity(membership).getId().toString()))
        ).build();
    }

    @GetMapping
    public ResponseEntity<Page<Membership>> getAllMemberships(Pageable pageable){
        return ResponseEntity.ok(membershipService.getPageOfAllEntities(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable String id){
        return ResponseEntity.ok(membershipService.getEntityById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMembership(@PathVariable String id, @RequestBody @Valid Membership membership){
        membershipService.updateEntity(id, membership);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable String id){
        membershipService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }
}

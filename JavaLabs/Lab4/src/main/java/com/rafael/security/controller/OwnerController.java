package com.rafael.security.controller;

import com.rafael.security.model.OwnerDTO;
import com.rafael.security.service.AuthService;
import com.rafael.security.service.OwnerService;
import com.rafael.security.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    private final AuthService authService;

    @Autowired
    public OwnerController(final OwnerService ownerService, final AuthService authService) {
        this.ownerService = ownerService;
        this.authService = authService;
    }

    @GetMapping
    public List<OwnerDTO> getOwners() {
        return ownerService.getAll();
    }

    @PutMapping
    public void updateOwner(@RequestParam(value = "id") long id, @RequestBody OwnerDTO owner) {
        if (authService.checkAuth(owner.getId())) {
            owner.setId(id);
            ownerService.updateById(owner);
        }
    }
}

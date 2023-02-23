package com.endava.tmd.bookclubproject.service;

import com.endava.tmd.bookclubproject.entity.Role;
import com.endava.tmd.bookclubproject.entity.User;
import com.endava.tmd.bookclubproject.repository.RoleRepository;
import com.endava.tmd.bookclubproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}

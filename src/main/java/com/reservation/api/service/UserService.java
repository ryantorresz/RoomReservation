package com.reservation.api.service;

import com.reservation.api.entity.User;
import com.reservation.api.exception.NotFoundException;
import com.reservation.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){this.repo = repo ; }

    public List<User> listAll() {return repo.findAll() ; }
    public User findById(Long id) {return repo.findById(id).orElseThrow(() -> new NotFoundException("User not Found"));}
    public User create(User u) {return repo.save(u) ; }
    public User update (Long id, User update) {
        User ex = findById(id);
        ex.setFullName(update.getFullName());
        ex.setEmail(update.getEmail());
        return repo.save(ex);
    }
    public void delete(Long id) {
        User ex = findById(id);
        repo.delete(ex);
    }
}


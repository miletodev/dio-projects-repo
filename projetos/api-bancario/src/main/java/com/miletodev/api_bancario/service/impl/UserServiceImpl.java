package com.miletodev.api_bancario.service.impl;

import com.miletodev.api_bancario.domain.model.User;
import com.miletodev.api_bancario.domain.repository.UserRepository;
import com.miletodev.api_bancario.service.UserService;
import com.miletodev.api_bancario.service.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {
    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Long id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Transactional
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null."));
        ofNullable(userToCreate.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null."));
        ofNullable(userToCreate.getCard()).orElseThrow(() -> new BusinessException("User card must not be null."));

        this.validateChangeableId(userToCreate.getId(), "created");
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new BusinessException("This account number already exists.");
        }
        if (userRepository.existsByCardNumber(userToCreate.getCard().getNumber())) {
            throw new BusinessException("This card number already exists.");
        }
        return this.userRepository.save(userToCreate);
    }

    @Transactional
    public User update(Long id, User userToUpdate) throws ChangeSetPersister.NotFoundException {
        this.validateChangeableId(id, "updated");
        User dbUser = this.findById(id);
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());

        return this.userRepository.save(dbUser);
    }

    @Transactional
    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        this.validateChangeableId(id, "deleted");
        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d cannot be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}

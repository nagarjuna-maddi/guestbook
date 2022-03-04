package com.guestbook.guestbookbackendsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guestbook.guestbookbackendsample.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String userName, String password);

}

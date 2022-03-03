package com.guestbook.guestbookbackendsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guestbook.guestbookbackendsample.model.GuestEntry;

@Repository
public interface AdminRepository extends JpaRepository<GuestEntry, Long> {
}

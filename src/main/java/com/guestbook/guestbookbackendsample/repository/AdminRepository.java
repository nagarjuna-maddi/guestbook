package com.guestbook.guestbookbackendsample.repository;

import org.springframework.data.repository.CrudRepository;

import com.guestbook.guestbookbackendsample.model.GuestEntry;

//@Repository
public interface AdminRepository extends CrudRepository<GuestEntry, Long> {

}

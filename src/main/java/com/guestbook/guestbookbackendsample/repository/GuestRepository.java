package com.guestbook.guestbookbackendsample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guestbook.guestbookbackendsample.model.GuestEntry;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntry, Long> {

//	@Modifying
//	@Query("select * from GuestEntry ge where ge.userId = :userId and ge.status = 'Approved'")
//	List<GuestEntry> viewAllApprovedGuestEntries(@Param(value = "userId") long userId);

	List<GuestEntry> findByUserIdAndStatus(Long userId, String status);

}

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.TUser;

@Repository
public interface TUserRepository extends JpaRepository<TUser, Integer> {

}

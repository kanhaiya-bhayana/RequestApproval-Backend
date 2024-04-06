package com.kanhaiya.requestapproval.repository;

import com.kanhaiya.requestapproval.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

}

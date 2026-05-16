package com.AuraSkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AuraSkin.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

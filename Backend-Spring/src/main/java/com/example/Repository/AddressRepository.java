package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}

package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.QrCode;

public interface QrCodeRepository extends CrudRepository<QrCode, Short> {

}

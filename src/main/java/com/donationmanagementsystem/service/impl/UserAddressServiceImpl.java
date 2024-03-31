package com.donationmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.donationmanagementsystem.entity.User;
import com.donationmanagementsystem.entity.UserAddress;
import com.donationmanagementsystem.exception.ResourceNotFoundException;
import com.donationmanagementsystem.payload.request.UserAddressRequest;
import com.donationmanagementsystem.payload.response.ApiResponse;
import com.donationmanagementsystem.payload.response.UserAddressResponse;
import com.donationmanagementsystem.repository.UserAddressRepository;
import com.donationmanagementsystem.service.UserAddressService;
import com.donationmanagementsystem.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserAddressRepository userAddressRepository;

    @Override
    public ResponseEntity<ApiResponse> create(UserAddressRequest userAddressRequest, User user) {
        try {
            UserAddress userAddress = this.modelMapper.map(userAddressRequest, UserAddress.class);
            userAddress.setUser(user);
            userAddressRepository.save(userAddress);
            return ResponseMessage.created("User address has been creeated successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError(null);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> update(UserAddressRequest userAddressRequest, Long userAddressId, User user) {
        try {
            UserAddress savedAddress = userAddressRepository.findByUserIdAndId(user.getId(), userAddressId)
                    .orElseThrow(() -> new ResourceNotFoundException("User Setting", "user id", user.getId()));

            savedAddress.setAddressLine1(userAddressRequest.getAddressLine1());
            savedAddress.setAddressLine2(userAddressRequest.getAddressLine2());
            savedAddress.setCity(userAddressRequest.getCity());
            savedAddress.setPostalCode(userAddressRequest.getPostalCode());
            savedAddress.setState(userAddressRequest.getState());
            userAddressRepository.save(savedAddress);
            return ResponseMessage.ok("User address has been updated successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError(null);
        }
    }

    @Override
    public ResponseEntity<UserAddressResponse> show(Long userAddressId, User user) {
        UserAddress savedAddress = userAddressRepository.findByUserIdAndId(user.getId(), userAddressId)
                .orElseThrow(() -> new ResourceNotFoundException("User Address", "user id", user.getId()));
        return new ResponseEntity<>(this.modelMapper.map(savedAddress, UserAddressResponse.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserAddressResponse>> all(User user) {
        List<UserAddress> userAddresses = userAddressRepository.findAllByUserId(user.getId());
        List<UserAddressResponse> userSettingResponses = userAddresses
                .stream()
                .map(
                        (address) -> this.modelMapper
                                .map(address, UserAddressResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<List<UserAddressResponse>>(userSettingResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long userAddressId, User user) {
        try {
            UserAddress saveAddress = userAddressRepository.findByUserIdAndId(user.getId(), userAddressId)
                    .orElseThrow(() -> new ResourceNotFoundException("User Address", "user id", user.getId()));
            if (saveAddress != null) {
                userAddressRepository.delete(saveAddress);
            }
            return ResponseMessage.ok("User address deleted successfully");
        } catch (Exception ex) {
            return ResponseMessage.internalServerError("");
        }
    }

    @Override
    public ResponseEntity<UserAddressResponse> byUser(Long id) {
        try {
            Optional<UserAddress> address = userAddressRepository.findByUserId(id);
            if (address.isPresent()) {
                UserAddressResponse userAddress = this.modelMapper.map(address.get(), UserAddressResponse.class);
                return new ResponseEntity<>(userAddress, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

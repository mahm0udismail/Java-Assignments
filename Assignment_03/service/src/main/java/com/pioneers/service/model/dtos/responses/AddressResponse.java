package com.pioneers.service.model.dtos.responses;

public record AddressResponse(
        String country,
        String governance,
        String city,
        int zipCode,
        String street,
        int buildingNumber,
        int floorNumber,
        int apartmentNumber
) {
}

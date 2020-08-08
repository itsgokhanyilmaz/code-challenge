package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.entity.Purchasing;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PurchasingMapper {
    PurchasingMapper map = Mappers.getMapper(PurchasingMapper.class);

    Purchasing purchasingRequestToPurchasing(PurchasingRequest purchasingRequest);

    PurchasingResponse purchasingToPurchasingResponse(Purchasing purchasing);
}

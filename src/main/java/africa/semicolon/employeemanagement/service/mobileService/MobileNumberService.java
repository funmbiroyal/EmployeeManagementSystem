package africa.semicolon.employeemanagement.service.mobileService;

import africa.semicolon.employeemanagement.data.dto.MobileNumberRequest;
import africa.semicolon.employeemanagement.data.dto.MobileNumberResponse;
import africa.semicolon.employeemanagement.data.model.MobileNumber;

import java.util.List;

public interface MobileNumberService {
    MobileNumberResponse addMobileNumber(MobileNumberRequest request);
    void deleteAllMobileNumber();
}

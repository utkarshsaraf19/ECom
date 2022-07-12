package com.ad.ecom.user.profile.controller;

import com.ad.ecom.common.stub.ResponseMessage;
import com.ad.ecom.user.profile.dto.AddressDto;
import com.ad.ecom.user.profile.dto.UpdatePwdEmailReq;
import com.ad.ecom.user.profile.dto.UserInfoDto;
import com.ad.ecom.user.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RolesAllowed({"USER", "ADMIN", "SELLER"})
    @GetMapping(path = "/fetch")
    public ResponseEntity<ResponseMessage> getUserAccountInfo() {
        return profileService.getUserAccountInfo();
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<ResponseMessage> updateUserInfo(@RequestBody @NotNull UserInfoDto userInfoDto) {
        return profileService.updateUserInfo(userInfoDto);
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<ResponseMessage> deleteUserProfile() {
        return profileService.deleteUserAccount();
    }

    @GetMapping(path = "/delete/confirm/{token}")
    public ResponseEntity<ResponseMessage> deleteUserProfileConfirmation(HttpSession httpSession, @PathVariable("token") String token) {
        System.out.println(token);
        return profileService.deleteUserAccountConfirmation(httpSession, token);
    }

    @GetMapping(path = "/update/pwd")
    public ResponseEntity<ResponseMessage> updatePassword() {
        return profileService.updatePassword();
    }

    @PatchMapping(path = "/update/pwd/confirm")
    public ResponseEntity<ResponseMessage> updatePasswordConfirmation(HttpSession httpSession, @RequestBody UpdatePwdEmailReq request) {
        return profileService.updatePasswordConfirmation(httpSession, request);
    }

    @GetMapping(path = "/update/email")
    public ResponseEntity<ResponseMessage> updateEmail() {
        return profileService.updateEmail();
    }

    @PatchMapping(path = "/update/email/confirm")
    public ResponseEntity<ResponseMessage> updateEmailConfirmation(@RequestBody UpdatePwdEmailReq request) {
        return profileService.updateEmailConfirmation(request);
    }

    @PostMapping(path = "/add/address")
    public ResponseEntity<ResponseMessage> storeAddresses(@NotNull @RequestBody List<AddressDto> addressList) {
        return profileService.storeAddresses(addressList);
    }

    @PatchMapping(path = "/update/address")
    public ResponseEntity<ResponseMessage> updateAddress(@NotNull @RequestBody AddressDto address) {
        return profileService.updateAddress(address);
    }

    @PostMapping(path = "/update/defaultAddress")
    public ResponseEntity<ResponseMessage> setDefaultAddress(@NotNull @NotBlank @NotEmpty @RequestBody String addressId) {
        return profileService.setDefaultAddress(addressId);
    }

}

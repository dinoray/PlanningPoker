package com.tchallenge.titansoft.planningpoker.model;

import java.util.List;

public class PincodeChecker {

    private final List<String> mExistedPincodeList;

    public PincodeChecker(List<String> existedPincodeList) {
        mExistedPincodeList = existedPincodeList;
    }

    public boolean hasPincode(String pincode) {
        return mExistedPincodeList.contains(pincode);
    }
}

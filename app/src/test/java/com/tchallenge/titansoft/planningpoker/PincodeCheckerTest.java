package com.tchallenge.titansoft.planningpoker;

import android.support.annotation.NonNull;

import com.tchallenge.titansoft.planningpoker.prsenter.PincodeChecker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PincodeCheckerTest {
    @Test
    public void InputNonExistPincode_Should_ReturnFalse() {
        List<String> existedPincodeList = createPincodeList();

        PincodeChecker pincodeChecker = new PincodeChecker(existedPincodeList);

        assertFalse(pincodeChecker.hasPincode("7777"));
    }

    @Test
    public void InputExistPincode_Should_ReturnTrue() {
        List<String> existedPincodeList = createPincodeList();

        PincodeChecker pincodeChecker = new PincodeChecker(existedPincodeList);

        assertTrue(pincodeChecker.hasPincode("8888"));
    }


    @NonNull
    private List<String> createPincodeList() {
        List<String> existedPincodeList = new ArrayList<>();
        existedPincodeList.add("9999");
        existedPincodeList.add("8888");
        return existedPincodeList;
    }
}
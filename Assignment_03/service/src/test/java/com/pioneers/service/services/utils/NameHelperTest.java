package com.pioneers.service.services.utils;

import com.pioneers.service.utils.NameHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameHelperTest {

    @Test
    void testBuildFullNameWhenValidNamesThenReturnsCombined() {
        String result = NameHelper.buildFullName("Mahmoud", "Ismail");
        assertEquals("Mahmoud Ismail", result);
    }

    @Test
    void testBuildFullNameWhenNamesHaveExtraSpacesThenReturnsTrimmed() {
        String result = NameHelper.buildFullName("  Mahmoud  ", "  Ismail  ");
        assertEquals("Mahmoud Ismail", result);
    }

    @Test
    void testBuildFullNameWhenFirstNameEmptyThenReturnsOnlyLastName() {
        String result = NameHelper.buildFullName("", "Ismail");
        assertEquals(" Ismail", result);
    }

}

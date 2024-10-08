package org.darekvu.lottoproject.domain.numberreceiver;

enum ValidationResponse {
    NOT_SIX_NUMBERS_GIVEN("PLEASE GIVE 6 NUMBERS"),
    NOT_IN_RANGE("NUMBERS MUST BE FROM 1 TO 99"),
    INPUT_SUCCESS("SUCCESS");

    final String info;

    ValidationResponse(String info) {
        this.info = info;
    }
}

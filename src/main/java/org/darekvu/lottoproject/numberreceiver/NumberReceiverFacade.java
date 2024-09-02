package org.darekvu.lottoproject.numberreceiver;

import lombok.AllArgsConstructor;
import org.darekvu.lottoproject.numberreceiver.dto.InputNumberResultDto;

import java.util.Set;

@AllArgsConstructor
class NumberReceiverFacade {
    private InputNumberValidator validator;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        return validator.validateInputNumbers(numbersFromUser) ? new InputNumberResultDto("success") : new InputNumberResultDto("failed");
    }


}

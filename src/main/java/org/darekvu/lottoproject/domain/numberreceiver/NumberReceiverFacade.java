package org.darekvu.lottoproject.domain.numberreceiver;

import lombok.RequiredArgsConstructor;
import org.darekvu.lottoproject.domain.numberreceiver.dto.InputNumberResponseDto;
import org.darekvu.lottoproject.domain.numberreceiver.dto.TicketDto;
import org.darekvu.lottoproject.domain.numberreceiver.exceptions.TicketNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class NumberReceiverFacade {
    private final InputNumberValidator validator;
    private final TicketRepository ticketRepository;
    private final DrawDateGenerator drawDateGenerator;
    private final UniqueIdGenerable ticketIdGenerator;

    public InputNumberResponseDto inputNumbers(Set<Integer> numbersFromUser) {

        List<ValidationResponse> validationResult = validator.validateInputNumbers(numbersFromUser);
        if (!validationResult.isEmpty()) {
            String message = validator.createErrorResponseMessage();
            return new InputNumberResponseDto(null, message);
        }


        LocalDateTime drawDate = drawDateGenerator.generateDrawDate();
        String ticketId = ticketIdGenerator.generateUniqueId();
        TicketDto generatedTicket = TicketDto.builder()
                .id(ticketId)
                .numbersFromUser(numbersFromUser)
                .drawDate(drawDate)
                .build();
        Ticket savedTicket = Ticket.builder()
                .id(ticketId)
                .numbersFromUser(numbersFromUser)
                .drawDate(drawDate)
                .build();
        ticketRepository.save(savedTicket);

        return new InputNumberResponseDto(generatedTicket, ValidationResponse.INPUT_SUCCESS.info);
    }

    public List<TicketDto> retrieveTicketsByDrawDate(LocalDateTime date) {
        return ticketRepository.findAllTicketsByDate(date).stream().map(TicketMapper::mapTicketToDto).toList();
    }

    public TicketDto retrieveTicketById(String ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with %s not found".formatted(ticketId)));
        return TicketDto
                .builder()
                .id(ticket.id())
                .drawDate(ticket.drawDate())
                .numbersFromUser(ticket.numbersFromUser())
                .build();
    }

    public LocalDateTime generateClosestDrawDate() {
        return drawDateGenerator.generateDrawDate();
    }
}

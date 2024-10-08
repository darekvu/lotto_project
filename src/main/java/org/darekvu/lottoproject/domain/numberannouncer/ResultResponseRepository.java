package org.darekvu.lottoproject.domain.numberannouncer;

import java.util.Optional;

public interface ResultResponseRepository {
    void save(ResultResponse response);

    boolean exists(String ticketId);

    Optional<ResultResponse> findById(String ticketId);
}

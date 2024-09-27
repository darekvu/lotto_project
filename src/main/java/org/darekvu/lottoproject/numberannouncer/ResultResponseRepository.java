package org.darekvu.lottoproject.numberannouncer;

import java.util.Optional;

public interface ResultResponseRepository {
    void save(ResultResponse response);

    boolean exists(String ticketId);

    Optional<ResultResponse> findById(String ticketId);
}

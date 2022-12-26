package ua.hryshko.service;

import ua.hryshko.model.History;

import java.util.List;

public interface HistoryService {

    void addHistory(History history);

    List<History> findByOwner(Long id);


    void removeHistory(Long id);
}

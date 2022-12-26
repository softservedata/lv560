package ua.hryshko.dao;

import ua.hryshko.model.History;

import java.util.List;


public interface HistoryDAO {

    void addHistory(History history);

    List<History> findByOwner(Long id);

    void removeHistory(Long id);
}

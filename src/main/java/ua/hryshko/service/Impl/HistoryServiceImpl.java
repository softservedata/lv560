package ua.hryshko.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.HistoryDAO;
import ua.hryshko.model.History;
import ua.hryshko.service.HistoryService;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryDAO historyDAO;

    @Transactional
    public void addHistory(History history) {
        historyDAO.addHistory(history);
    }

    @Transactional
    public List<History> findByOwner(Long id) {
        return historyDAO.findByOwner(id);
    }

    @Transactional
    public void removeHistory(Long id) {
        historyDAO.removeHistory(id);

    }
}

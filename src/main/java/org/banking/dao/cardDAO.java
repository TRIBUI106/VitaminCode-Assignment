package org.banking.dao;

import org.banking.model.Card;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cardDAO implements DAO<Card, Integer> {

    private DBHelper db;

    public cardDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Card entity) throws SQLException {
        String sql = "INSERT INTO cards (account_id, card_number, expiry_date) VALUES (?, ?, ?)";
        return db.exeuteUpdate(sql, entity.getAccountId(), entity.getCardNumber(), entity.getExpiryDate());
    }

    @Override
    public Integer update(Card entity, Integer id) throws SQLException {
        String sql = "UPDATE cards SET account_id = ?, card_number = ?, expiry_date = ? WHERE id = ?";
        return db.exeuteUpdate(sql, entity.getAccountId(), entity.getCardNumber(), entity.getExpiryDate(), id);
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "DELETE FROM cards WHERE id = ?";
        return db.exeuteUpdate(sql, id);
    }

    @Override
    public List<Card> getAll() throws SQLException {
        String sql = "SELECT id, account_id, card_number, expiry_date FROM cards";
        ResultSet rs = db.excuteQuery(sql);
        List<Card> cards = new ArrayList<>();
        while (rs.next()) {
            Card card = new Card();
            card.setId(rs.getInt("id"));
            card.setAccountId(rs.getInt("account_id"));
            card.setCardNumber(rs.getString("card_number"));
            card.setExpiryDate(rs.getDate("expiry_date"));
            cards.add(card);
        }
        return cards;
    }

    @Override
    public Card getById(Integer id) throws SQLException {
        String sql = "SELECT id, account_id, card_number, expiry_date FROM cards WHERE id = ?";
        ResultSet rs = db.excuteQuery(sql, id);
        Card card = null;
        if (rs.next()) {
            card = new Card();
            card.setId(rs.getInt("id"));
            card.setAccountId(rs.getInt("account_id"));
            card.setCardNumber(rs.getString("card_number"));
            card.setExpiryDate(rs.getDate("expiry_date"));
        }
        return card;
    }
}
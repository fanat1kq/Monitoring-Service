package org.example.in.dto;

import java.util.List;


public class TransactionHistoryResponse {

    private String playerLogin;
    private List<IndicationsDTO> transactions;

    public TransactionHistoryResponse() {
    }

    public TransactionHistoryResponse(String playerLogin, List<IndicationsDTO> transactions) {
        this.playerLogin = playerLogin;
        this.transactions = transactions;
    }

    public String getPlayerLogin() {
        return playerLogin;
    }

    public void setPlayerLogin(String playerLogin) {
        this.playerLogin = playerLogin;
    }

    public List<IndicationsDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<IndicationsDTO> transactions) {
        this.transactions = transactions;
    }
}

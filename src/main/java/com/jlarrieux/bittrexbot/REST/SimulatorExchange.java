package com.jlarrieux.bittrexbot.REST;



import com.google.gson.Gson;
import com.jlarrieux.bittrexbot.Util.Constants;
import com.jlarrieux.bittrexbot.simulation.DAO.DBExchangeDAOImpl;
import com.jlarrieux.bittrexbot.simulation.DAO.IDBExchangeDAO;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Data
@Log
@Qualifier(Constants.SIMULATOR)
@Profile("!live")
public class SimulatorExchange  implements ExchangeInterface{

    private IDBExchangeDAO dbExchangeDAO;

    public SimulatorExchange(){
        dbExchangeDAO = new DBExchangeDAOImpl();
    }

    @Override
    public Response getMarkets() {
        return null;
    }



    @Override
    public Response getMarketSummary(String marketName) {
        return new Response(dbExchangeDAO.getMarketSummary(marketName));
    }



    @Override
    public Response getMarketOrderBook(String marketName) {
        return new Response(dbExchangeDAO.getMarketOrderBook(marketName));
    }



    @Override
    public Response getMarketSummaries() {
        return new Response(dbExchangeDAO.getMarketSummaries());
    }



    @Override
    public Response getOpenOrders() {
        return null;
    }



    @Override
    public Response getBalance(String currency) {
        return null;
    }



    @Override
    public Response getBalances() {
        return null;
    }



    @Override
    public Response cancelOrder(String id) {
        return null;
    }



    @Override
    public Response buy(String marketName, double quantity, double price) {
        return createBuyOrSellResponse();
    }



    @Override
    public Response sell(String marketName, double quantity, double price) {
        return createBuyOrSellResponse();
    }



    @Override
    public Response getOrderHistory(String marketName) {
        return null;
    }



    @Override
    public Response getOrder(String uuid) {
        return null;
    }

    private Response createBuyOrSellResponse(){
        String uuid = UUID.randomUUID().toString();
        Result result = new Result(uuid);
        Response response = new Response(true, new Gson().toJson(result), "");
        return response;
    }

    public class Result {

        private String uuid;

        public String getUuid() {
            return uuid;
        }

        public Result(String uuid) {
            this.uuid = uuid;
        }
        }
}

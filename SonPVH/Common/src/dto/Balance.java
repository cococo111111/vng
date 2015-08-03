/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author root
 */
public class Balance implements java.io.Serializable {

    private int userId;
    private double main = 0;
    private double promotion = 0;
    private double game = 0;
    private long lastTxId;

    public Balance() {
    }

    public Balance(int _userid, double _money, double _promotion, double game) {
        this.userId = _userid;
        this.main = _money;
        this.promotion = _promotion;
        this.game = game;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public double getMain() {
        return main;
    }

    public void setMain(double main) {
        this.main = main;
    }

    public double getGame() {
        return game;
    }

    public void setGame(double game) {
        this.game = game;
    }

    public long getLastTxId() {
        return lastTxId;
    }

    public void setLastTxId(long lastTxId) {
        this.lastTxId = lastTxId;
    }
}

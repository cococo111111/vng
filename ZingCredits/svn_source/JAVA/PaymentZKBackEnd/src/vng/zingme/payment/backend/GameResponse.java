/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.backend;

/**
 *
 * @author root
 */
public class GameResponse {

    public int code;
    public String des;

    public GameResponse(int code, String des) {
        this.code = code;
        this.des = des;
    }
}

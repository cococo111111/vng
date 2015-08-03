/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daoService;

/**
 *
 * @author root
 */
public interface IDao< T> {

    // implement simple function CRUD
    //  void create(T t);
    //  void delete(T t);
    T find(int id);

    boolean insert(T t);
//    T TtoString(String a);
}

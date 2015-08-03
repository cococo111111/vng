/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zooService;

/**
 *
 * @author root
 */
public interface IZoo<T> {

    /**
     *
     * @param t
     * @param path
     */
    public boolean create(T t, String path);

    public T get(int id, String path);

    public boolean delete(int id, String path);
}

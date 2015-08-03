/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producerService;

/**
 *
 * @author root
 */
public interface IProducer<T> {

    public boolean sendMessage(T t);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.apps;

import java.util.List;
import org.apache.thrift.TException;
import vng.zingme.payment.thrift.TAppServer;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 *
 * @author root
 */
public class AppsServiceHandler implements TAppServer.Iface {

    public String getAppName(String appID) throws TException {
        return AppsServiceModel.getAppInfo(appID).appName;
    }

    public T_AppInfo getAppInfo(String appID) throws TException {
        return AppsServiceModel.getAppInfo(appID);
    }

    public String registerGameServer(T_AppInfo appInfo, String adminSig, final boolean isNewKey) throws TException {
        return AppsServiceModel.registerGameServer(appInfo, adminSig, isNewKey);
    }

    public int registerPayLetterServer(T_AppInfo appInfo, String adminSig) throws TException {
        return AppsServiceModel.registerPayLetterServer(appInfo, adminSig);
    }

    public List<T_AppInfo> getAllAppInfo() throws TException {

        return AppsServiceModel.getAllAppInfo();
    }

    public void addIdToWhitelist(final String appID, final int userID) throws TException {
        AppsServiceModel.addIdToWhitelist(appID, userID);
    }

    public void addIdsToWhitelist(final String appID, final List<Integer> lsUserID) throws TException {
        AppsServiceModel.addIdsToWhitelist(appID, lsUserID);
    }

    public void removeIdFromWhitelist(final String appID, final int userID) throws TException {
        AppsServiceModel.removeIdFromWhitelist(appID, userID);
    }
}

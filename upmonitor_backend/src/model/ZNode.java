/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author locth2
 */
public class ZNode {

    private String name = "";
    private String path = "";
    private String child = "";
    private String description = "";
    private String zmeContacts = "";
    private String smsContacts = "";
    private String mailContacts = "";
    private String serviceDependencies = "";
    private String extras = "";
    private String serverName = "";
    private String serverIp = "";
    private String properties = "";
    private String configuration = "";
    private String parent = "";
    private String urlLive = "";
    private String jmxString = "";
    private int level = 0;
    private int online = 1;
    private int isPersistent = 0;
    private int createtime = 0;
    private int deletetime = 0;
    private int isDel = 0;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the child
     */
    public String getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(String child) {
        this.child = child;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    public int getOnline() {
        return online;
    }

    /**
     * @param online
     */
    public void setOnline(int online) {
        this.online = online;
    }

    /**
     * @return the persistent
     */
    public int getIsPersistent() {
        return isPersistent;
    }

    /**
     * @param persistent the persistent to set
     */
    public void setPersistent(int isPersistent) {
        this.isPersistent = isPersistent;
    }

    /**
     * @return the createtime
     */
    public int getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime the createtime to set
     */
    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    /**
     * @return the deletetime
     */
    public int getDeletetime() {
        return deletetime;
    }

    /**
     * @param deletetime the deletetime to set
     */
    public void setDeletetime(int deletetime) {
        this.deletetime = deletetime;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the zmeContacts
     */
    public String getZmeContacts() {
        return zmeContacts;
    }

    /**
     * @param zmeContacts the zmeContacts to set
     */
    public void setZmeContacts(String zmeContacts) {
        this.zmeContacts = zmeContacts;
    }

    /**
     * @return the smsContacts
     */
    public String getSmsContacts() {
        return smsContacts;
    }

    /**
     * @param smsContacts the smsContacts to set
     */
    public void setSmsContacts(String smsContacts) {
        this.smsContacts = smsContacts;
    }

    /**
     * @return the mailContacts
     */
    public String getMailContacts() {
        return mailContacts;
    }

    /**
     * @param mailContacts the mailContacts to set
     */
    public void setMailContacts(String mailContacts) {
        this.mailContacts = mailContacts;
    }

    /**
     * @return the serviceDependencies
     */
    public String getServiceDependencies() {
        return serviceDependencies;
    }

    /**
     * @param serviceDependencies the serviceDependencies to set
     */
    public void setServiceDependencies(String serviceDependencies) {
        this.serviceDependencies = serviceDependencies;
    }

    /**
     * @return the extras
     */
    public String getExtras() {
        return extras;
    }

    /**
     * @param extras the extras to set
     */
    public void setExtras(String extras) {
        this.extras = extras;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return the serverIp
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * @param serverIp the serverIp to set
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    /**
     * @return the properties
     */
    public String getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(String properties) {
        this.properties = properties;
    }

    /**
     * @return the configuration
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    /**
     * @return the parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * @return the urlLive
     */
    public String getUrlLive() {
        return urlLive;
    }

    /**
     * @param urllive the urlLive to set
     */
    public void setUrlLive(String urlLive) {
        this.urlLive = urlLive;
    }

    /**
     * @return the jmxString
     */
    public String getJmxString() {
        return jmxString;
    }

    /**
     * @param jmxString the jmxString to set
     */
    public void setJmxString(String jmxString) {
        this.jmxString = jmxString;
    }

    /**
     * @return the isDel
     */
    public int getIsDel() {
        return isDel;
    }

    /**
     * @param isDel the isDel to set
     */
    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}

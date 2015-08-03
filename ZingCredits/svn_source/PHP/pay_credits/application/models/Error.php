<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Error
 *
 * @author root
 */
require_once APPLICATION_PATH.'/models/Manager.php';
class Error extends Manager{
    //put your code here
    public function insert($code, $error){
        $data = array('code' => $code, 'error' => $error);
        $this->dbConn->insert('errors', $data);
    }
    public function update($errID, $code = null, $error = null){
        $data = array();
        if($code != null){
            $data['code'] = $code;
        }
        if($error != null){
            $data['error'] = $error;
        }
        $where = "errID='" . $errID . "'";
        $this->dbConn->update('errors', $data, $where);
    }
    
    public function delete($errID){
        $where = "errID='" . $errID . "'";
        $this->dbConn->delete('errors', $where);
    }

    public function getError($errID = null){
        $sql = "select * from errors ";
        if($errID != null){
            $sql .= " where errID='".$errID . "'";
        }
        return $this->dbConn->fetchAll($sql);
    }
     public function getErrorByCode($code){
        $sql = "select * from errors ";
        $sql .= " where code=?";
        $param = array($code);
        return $this->dbConn->fetchAll($sql, $param);
    }
}
?>

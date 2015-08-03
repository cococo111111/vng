<?php
class Models_XMLUser
{   	
	public function getAllUser()
	{			
		$allUser = array();
		$doc = new DOMDocument();
  		print_r(XML_DB_PATH);
                $doc->load( XML_DB_PATH );
                $users = $doc->getElementsByTagName( "user" );
		foreach( $users as $user )
		{  
			$usernames = $user->getElementsByTagName( "username" );
			$username = $usernames->item(0)->nodeValue;

			$passwords = $user->getElementsByTagName( "password" );
			$password = $passwords->item(0)->nodeValue;

//			$userids = $user->getElementsByTagName( "userid" );
//			$userid = $userids->item(0)->nodeValue;
			$userid = $user->getAttribute("userid");			

			$userroles = $user->getElementsByTagName( "userrole" );
			$userrole = $userroles->item(0)->nodeValue;
			
			$controlblocks = $user->getElementsByTagName( "controlblock" );
			$controlblock = $controlblocks->item(0)->nodeValue;
			
			$names = $user->getElementsByTagName( "name" );
			$name = $names->item(0)->nodeValue;
			
			$temp = new stdClass();
			$temp->userid = $userid;			
			$temp->username = $username;
			$temp->password = $password;
			$temp->userrole = $userrole;
			$temp->controlblock = $controlblock;
			$temp->name = $name;
			
			$allUser[count($allUser)] = $temp;
		}
		return $allUser;
	}
	
	public function authenticate($username,$password)
    {
		$result = new stdClass();
		$result->valid = false;
		$result->user_info = new stdClass();
		
		$allUser = $this->getAllUser();
		
		foreach($allUser as $user)
		{
			if($user->username === $username && $user->password === $password)
			{
				$result->valid = true;
				$result->user_info  = $user;
				break;
			}
		}
		return $result;
    }

    public function getUserInfoFromUserName($username)
    {        
        $result = new stdClass();
//       	$result->userid = $identity["userid"];
//       	$result->username = $identity["username"];
//       	$result->userrole = $identity["userrole"];
		return $result;
    }
    
	public function getUserInfoFromUserId($inputUserId)
    {		
    	try 
    	{
	    	if(empty($inputUserId))
	    		return -1;
				
			$doc = new DOMDocument();
	  		$doc->load( XML_DB_PATH );
	  		//find the position of tag user
			$users = $doc->getElementsByTagName( "user" );
			$userIndex = -1;
			$count = 0;
			foreach( $users as $user )
			{  
				$currUserId = $user->getAttribute("userid");
				if($currUserId==$inputUserId)
				{
					$userIndex=$count;
					break;
				}
				$count++;
			}
			//Can not find the user.		
			if($userIndex==-1)
				return ERROR;
	
			$usersNode = $doc->getElementsByTagName( "users" )->item(0);
			$getUser=$usersNode->getElementsByTagName("user")->item($userIndex);
    		
			//Get Info..
			$usernames = $getUser->getElementsByTagName( "username" );
			$username = $usernames->item(0)->nodeValue;

			$passwords = $getUser->getElementsByTagName( "password" );
			$password = $passwords->item(0)->nodeValue;
			
			$userid = $getUser->getAttribute("userid");			

			$userroles = $getUser->getElementsByTagName( "userrole" );
			$userrole = $userroles->item(0)->nodeValue;
			
			$controlblocks = $getUser->getElementsByTagName( "controlblock" );
			$controlblock = $controlblocks->item(0)->nodeValue;
			
			$names = $getUser->getElementsByTagName( "name" );
			$name = $names->item(0)->nodeValue;
			
			$result = new stdClass();
			$result->userid = $userid;			
			$result->username = $username;
			$result->password = $password;
			$result->userrole = $userrole;
			$result->controlblock = $controlblock;
			$result->name = $name;		
			//END Get Info
			
			return $result;
    	}
    	catch (Exception $ex)
    	{
    		return ERROR;
    	}
		
    }
    
    public function createUser($userData)
    {
    	try
    	{    		    	
	    	if(empty($userData))
		    	return ERROR;
		    	
	    	$doc = new DOMDocument();
	  		$doc->load( XML_DB_PATH );
	  		
	  		$user = $doc->createElement( "user" );
	  		$user->setAttribute("userid",time()+"");
	  		
	  		$username = $doc->createElement( "username" );	  		
	  		$password = $doc->createElement( "password" );
	  		$name = $doc->createElement( "name" );
	  		$userrole = $doc->createElement( "userrole" );
	  		$controlblock = $doc->createElement( "controlblock" );  		
	  		$username->appendChild(
				$doc->createTextNode( $userData['username'] )
			);
			$password->appendChild(
				$doc->createTextNode( $userData['password'] )
			);
			$name->appendChild(
				$doc->createTextNode( $userData['name'] )
			);
			$userrole->appendChild(
				$doc->createTextNode( $userData['userrole'] )
			);
			$controlblock->appendChild(
				$doc->createTextNode( $userData['controlblock'] )
			);
	  		$user->appendChild( $username );
	  		$user->appendChild( $password );
	  		$user->appendChild( $name );
	  		$user->appendChild( $userrole );
	  		$user->appendChild( $controlblock );
	  		
	  		$usersNode = $doc->getElementsByTagName( "users" )->item(0);
	  		$usersNode->appendChild($user);
	  		$doc->save(XML_DB_PATH);
    	}
    	catch(Exception $ex)
    	{
    		return ERROR;
    	}
    }
    
    public function deleteUser($userId)
    {    	
    	try 
    	{
	    	if(empty($userId))
	    		return ERROR;
				
			$doc = new DOMDocument();
	  		$doc->load( XML_DB_PATH );
	  		//find the position of tag user
			$users = $doc->getElementsByTagName( "user" );
			$userIndex = -1;
			$count = 0;
			foreach( $users as $user )
			{  
				$currUserId = $user->getAttribute("userid");
				if($currUserId==$userId)
				{
					$userIndex=$count;
					break;
				}
				$count++;
			}
			//Can not find the user.		
			if($userIndex==-1)
				return ERROR;
	
			$usersNode = $doc->getElementsByTagName( "users" )->item(0);
			$deleteUser=$usersNode->getElementsByTagName("user")->item($userIndex);
			$usersNode->removeChild($deleteUser);
			$doc->save(XML_DB_PATH);

			return SUCCESS;
    	}
    	catch (Exception $ex)
    	{
    		return ERROR;
    	}
    }
    
    public function updateUser($userData,$userId)
    {
       	try 
    	{
	    	if(empty($userData)||empty($userId))
	    		return ERROR;
				
			$doc = new DOMDocument();
	  		$doc->load( XML_DB_PATH );
	  		//find the position of tag user
			$users = $doc->getElementsByTagName( "user" );
			$userIndex = -1;
			$count = 0;
			foreach( $users as $user )
			{  
				$currUserId = $user->getAttribute("userid");
				if($currUserId==$userId)
				{
					$userIndex=$count;
					break;
				}
				$count++;
			}
			//Can not find the user.		
			if($userIndex==-1)
				return ERROR;
	
			$usersNode = $doc->getElementsByTagName( "users" )->item(0);
			$updateUser= $usersNode->getElementsByTagName("user")->item($userIndex);

			//Change..
			if(!empty($userData['userrole']))
			{
				$userroles = $updateUser->getElementsByTagName( "userrole" );		
				$userroles->item(0)->nodeValue = $userData['userrole'];
			}
			
			$controlblocks = $updateUser->getElementsByTagName( "controlblock" );				
			$controlblocks->item(0)->nodeValue = $userData['controlblock'];
			
    		if(!empty($userData['name']))
			{
				$names = $updateUser->getElementsByTagName( "name" );				
				$names->item(0)->nodeValue = $userData['name'];
			}		
			//END Change..
			
			$doc->save(XML_DB_PATH);

			return SUCCESS;
    	}
    	catch (Exception $ex)
    	{
    		return ERROR;
    	}
    }
    
    public function changePass($oldPass,$newPass,$confirmPass,$passLength,$userId)
    {
    	try
    	{
	    	if($oldPass==md5(""))
	    		return CHANGEPASS_EMPTY_CURR_PASS;
	    	if($newPass==md5(""))
	    		return CHANGEPASS_EMPTY_NEW_PASS;
	    	if($confirmPass==md5(""))
	    		return CHANGEPASS_EMPTY_CONFIRM_PASS;
	    	if(empty($userId))
	    		return ERROR;
	    		
	    	$doc = new DOMDocument();
		  	$doc->load( XML_DB_PATH );
		  	
		  	//find the position of tag user
			$users = $doc->getElementsByTagName( "user" );
			$userIndex = -1;
			$count = 0;
			foreach( $users as $user )
			{  
				$currUserId = $user->getAttribute("userid");
				if($currUserId==$userId)
				{
					$userIndex=$count;
					break;
				}
				$count++;
			}
			//Can not find the user.		
			if($userIndex==-1)
				return ERROR;
				
			$usersNode = $doc->getElementsByTagName( "users" )->item(0);
			$updateUser= $usersNode->getElementsByTagName("user")->item($userIndex);
			
			$passwords 	= $updateUser->getElementsByTagName( "password" );
			$currentPassword 	= $passwords->item(0)->nodeValue;
			
			//If old pass is invalid
			if($currentPassword!=$oldPass)
				return CHANGEPASS_WRONG_OLD_PASS;
			if($newPass!=$confirmPass)
	    		return CHANGEPASS_CONFIRM_NOT_MATCH;	    		
	    	if($passLength<5)
	    		return CHANGEPASS_PASS_NOT_LENGTH_ENOUGH;
	   		//Change..
	   		$passwords = $updateUser->getElementsByTagName( "password" );
			$passwords->item(0)->nodeValue = $newPass;
			$doc->save(XML_DB_PATH);	
			return SUCCESS;
    	}
    	catch(Exception $ex)
    	{
    		return ERROR;
    	}
    }
    
    public function resetPass($userId)
    {
    	try
    	{	    		
	    	$doc = new DOMDocument();
		  	$doc->load( XML_DB_PATH );
		  	
		  	//find the position of tag user
			$users = $doc->getElementsByTagName( "user" );
			$userIndex = -1;
			$count = 0;
			foreach( $users as $user )
			{  
				$currUserId = $user->getAttribute("userid");
				if($currUserId==$userId)
				{
					$userIndex=$count;
					break;
				}
				$count++;
			}
			//Can not find the user.		
			if($userIndex==-1)
				return ERROR;
				
			$usersNode = $doc->getElementsByTagName( "users" )->item(0);
			$updateUser= $usersNode->getElementsByTagName("user")->item($userIndex);
			
			$username 	= $updateUser->getElementsByTagName( "username" )->item(0)->nodeValue;
	   		//Change password equal username.
	   		$passwords = $updateUser->getElementsByTagName( "password" );
			$passwords->item(0)->nodeValue = md5($username);
			$doc->save(XML_DB_PATH);
			return SUCCESS;
    	}
    	catch(Exception $ex)
    	{
    		return ERROR;
    	}
    }
    
    public function checkUpdateUser($userData)
    {
    	$success = true;
    	$error = array();
    	$result = array();
    	//Check if name is empty
    	if(empty($userData["name"]))
    	{
    		$success = false;
    		$error[] = "Name is empty";
    	}
    	$result["success"] = $success;
    	$result["error"] = $error;
    	return $result;
    }
    
    public function checkCreateUser($userData)
    {
    	$success = true;
    	$error = array();
    	$result = array();
    	$allUser = $this->getAllUser();
    	//Check if username valid
    	if(empty($userData["username"]))
    	{
    		$success = false;
    		$error[] = "Username is empty";
    	}
    	else
    	//Check if username exists
    	foreach($allUser as $user)
    	{
    		if($user->username == $userData["username"])
    		{
    			$success = false;
    			$error[] = "Username exists";
    		}
    	}    	
    	//Check if pass long enough
    	if($userData["passlength"]<8)
    	{
    		$success = false;
    		$error[] = "Password must be more than 7 characters";
    	}
    	else
    	//Check if pass & confirm don't match
    	if($userData["password"]!=$userData["confirmpassword"])
    	{
    		$success = false;
    		$error[] = "Password and confirm do not match";
    	}
    	//Check if name is empty
    	if(empty($userData["name"]))
    	{
    		$success = false;
    		$error[] = "Name is empty";
    	}
    	$result["success"] = $success;
    	$result["error"] = $error;
    	return $result;
    }
    
    //$controlblock : string of controlblock in db
    public static function getACLArr($controlblock)
    {   
    	$result = new stdClass();
    	
    	$result->systemACL = array();
    	$result->appACL = array();
    	if(empty($controlblock))
    		return $result;
    	
    	$RemoveStrArr = explode ( ";", $controlblock);
		unset($RemoveStrArr[count($RemoveStrArr)-1]);
    	foreach ($RemoveStrArr as $item) {
			unset($decodeItemArr);			
			unset($systemId);
			unset($appId);
			
			$decodeItemArr = explode ( "_", $item);
			$systemId = $decodeItemArr[0];
			$result->systemACL["".$systemId] = true;
			if(count($decodeItemArr)>1)
			{	
				$appId = $decodeItemArr[1];
				$result->appACL["".$appId] = true;
			}
		}
		return $result;
    }
    
    public static function getRoleName($roleId)
    {
    	switch($roleId)
    	{
    		case ACL_ROLE_SYSTEMADMIN:
    			return "System admin";
    		case ACL_ROLE_ADMIN:
    			return "Admin";
    		default: 
    			return "User";
    	}
    }
}

?>

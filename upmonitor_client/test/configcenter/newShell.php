<?php
     include_once("header1.php");
?>
<table width="100%" bgcolor="#FDFDFD">
  <tr>
    <td><p style="font-size:16px; font-weight:bold; padding-left:8px"><?php 
                                                                        if(isset($_GET["prod"]) && !empty($_GET["prod"])){
                                                                                if ($_GET["prod"] == 'zme')
                                                                                        echo "Zing Me Server ";
                                                                                if ($_GET["prod"] == 'zc')
                                                                                        echo "Zing Chat Server ";
                                                                                if ($_GET["prod"] == 'zml')
                                                                                        echo "Zing Mail Server ";
                                                                                if ($_GET["prod"] == 'zpp')
                                                                                        echo "PassPort Server ";
                                                                                if ($_GET["prod"] == 'zpm')
                                                                                        echo "Payment Server ";
                                                                                if ($_GET["prod"] == 'csm')
                                                                                        echo "CSM Server ";
                                                                        }else   echo "UCOM Server Info"; ?></p></td>
    <form id="formsearch" name="formsearch" method="get" action="">
       <td width="10px"><select id="fieldsearch" name="fieldsearch">
               <?php    if (isset($_REQUEST["fieldsearch"] ) && $_REQUEST["fieldsearch"] ==1) echo " <option selected value=1>IP</option>"; else echo " <option value=1>IP</option>";
                        if (isset($_REQUEST["fieldsearch"] ) && $_REQUEST["fieldsearch"] ==2) echo " <option selected value=2>Free Ram (MB)</option>"; else echo " <option value=2>Free Ram (MB)</option>";
                        if (isset($_REQUEST["fieldsearch"] ) &&  $_REQUEST["fieldsearch"] ==4) echo " <option selected value=4>Free Disk (GB)</option>"; else echo " <option value=4>Free Disk (GB)</option>";
                        if (isset($_REQUEST["fieldsearch"] ) && $_REQUEST["fieldsearch"] ==3) echo " <option selected value=3>Load Avarage</option>"; else echo " <option value=3> Load Avarage</option>";
                        if (isset($_REQUEST["fieldsearch"] ) && $_REQUEST["fieldsearch"] ==6) echo " <option selected value=6>Service</option>"; else echo " <option value=6> Service</option>";
                        if (isset($_REQUEST["fieldsearch"] ) && $_REQUEST["fieldsearch"] ==5) echo " <option selected value=5>OS</option>"; else echo " <option value=5> Load Avarage</option>";
                ?>
       </select> </td>
       <?php
       if (empty($_REQUEST["txtsearch"])) echo "<td width='150px'><input type='text' class='type'  name='txtsearch' id='txtsearch' /></td>";
        else {  $txtsearch_tmp = $_REQUEST["txtsearch"];
                echo "<td width='150px'><input type='text' class='type'  name='txtsearch' id='txtsearch' value='$txtsearch_tmp'  /></td>";
        }
        ?>
       <td width="50px"><button  type="submit" id="searchbutton" value="" name="searchbutton">Search</button></td>
    </form>
       <td width="20px"><a title="Save as" href="download.php<?php echo "?input=".trim($_REQUEST["txtsearch"]); ?> " id="dowloadButton" class="LogoutButton"> <img src="IMG/download.png" width="28" height="28" /></a></td>
  </tr>
</table>
<?php   
        include_once("connectdb.php");
        $dp = new DataProvider();
        $addret = "";
        if(isset($_REQUEST["searchbutton"])  && !empty($_REQUEST["txtsearch"]) && (!isset($_REQUEST["fieldsearch"]) || ($_REQUEST["fieldsearch"] != 3 && $_REQUEST["fieldsearch"] != 4 && $_REQUEST["fieldsearch"] != 2  && $_REQUEST["fieldsearch"] != 5  && $_REQUEST["fieldsearch"] != 6 ))){
          $tmp = trim($_REQUEST["txtsearch"]);
          $string = $_REQUEST["txtsearch"];
          $string = $_REQUEST["txtsearch"];
          $table = 'server_info';
          if (isset($_REQUEST["dayview"])){
                switch ($_REQUEST["dayview"]) {
                   case '1dayago':
                        $table = 'server_info_1dayago';
                         break;
                   case '2dayago':
                        $table = 'server_info_2dayago';
                         break;
                   case '3dayago':
                        $table = 'server_info_3dayago';
                         break;
                   case '4dayago':
                        $table = 'server_info_4dayago';
                         break;
                   case '5dayago':
                        $table = 'server_info_5dayago';
                         break;
                   case '6dayago':
                        $table = 'server_info_6dayago';
                         break;
                   case '7dayago':
                        $table = 'server_info_7dayago';
                         break;

                }
          }
          if ($tmp[0] == "|") 
                $strSQL = "Select * From ".$table." Where ip_private like '".substr( $tmp, 1)."'";
          else 
                $strSQL = "Select * From ".$table." Where ip_private like '%$string%'";
          $result = $dp->ExecuteQuery($strSQL);
          if($result){
                if(mysql_num_rows($result) == 0){
                        $notfound  = "Not Found";
                        echo "<h2><center>$notfound</center></h2>";
                }else{
                    if (mysql_num_rows($result) == 1){
                      if (isset($_REQUEST["addbutton"]) && !empty($_REQUEST["product"]) && isset($_REQUEST["ipadd"])){
                        $ipadd =  $_REQUEST["ipadd"];
                        $result_add = $dp->UpdateRecord("Update server_info set product = '".strtoupper($_REQUEST["product"])."' where ip_private = '".$ipadd."'");
                        if ($result_add)
                          $addret =  "<h2><center>Added!</center></h2>";
                        else{
                          $addret  = "<h2><center>Error!</center></h2>";
                        }
                      }
                     if (isset($_REQUEST["delbutton"]) && isset($_REQUEST["ipadd"])){
                        $result_del = $dp->ExecuteQuery("delete from server_info where ip_private = '".$_REQUEST["ipadd"]."';");
                        if ($result_del){
                          echo "<h2><center>Deleted!</center></h2>";
                          die();
                        }
                        else{
                          $addret  = "<h2><center>Error!</center></h2>";
                        } 
                     }
                      $result_tmp =  $dp->ExecuteQuery($strSQL);
                      while($rows = mysql_fetch_assoc($result_tmp)){               
                        $ipadd =  $rows["ip_private"];
                      }
                      echo '<div style="float:left;width:100%"><table bgcolor="#FDFDFD" width="100%" style="float:left;"> <tbody><tr>                   
                        <form action="" method="get" name="formadd" id="formadd">';
                      if (!isset($_REQUEST["dayview"]) || ($_REQUEST["dayview"] !="1dayago" && $_REQUEST["dayview"] !="2dayago")){
                      echo '
                        <td width="100px"><select name="product">
                                  <option value="">---</option>
                                  <option value="zme">Zing Me</option>
                                  <option value="zc">Zing Chat</option>
                                  <option value="zml">Zing Mail</option>
                                  <option value="zpp">Passport</option>
                                  <option value="zpm">Payment</option>
                                  <option value="csm">CSM</option>
                        </select></td> 
                        <td width="75px"><button style="width:70px"  name="addbutton" id="addbutton" type="submit">Add</button></td>
                        <input type="hidden" id="ipadd" name ="ipadd" value="'.$ipadd.'">
                        <td width="*"><div style="align:center; width:*">'.$addret.'</div></td>';
                      if (!isset($_REQUEST["checkportbutton"])){
                      echo '<td width="75px" align="right"><button title="Check port available " style="width:80px" name="checkportbutton" id="checkportbutton" type="submit">Check </button></td>';
                      }
                      else{
                        echo '<td width="75px" align="right"><button style="width:80px" name="uncheckportbutton" id="uncheckportbutton" type="submit">Uncheck</button></td>';
                      }
                      if (!isset($_REQUEST["sortbutton"])){ 
                        echo '<td width="75px" align="right"><button title="Sort & uniq by PID " style="width:80px" name="sortbutton" id="sortbutton" type="submit">Sort</button></td>';
                      }
                      else{
                        echo '<td width="75px" align="right"><button style="width:80px" name="unsortbutton" id="unsortbutton" type="submit">Full</button></td>';
                      }
                     
                      echo '
                        <td width="75px" align="right"><button style="width:80px" name="delbutton" id="delbutton" type="submit">Delete</button></td>
                      ';
                      }
                      echo '
                        <input type="hidden" id="txtsearch" name ="txtsearch" value="'.$string.'">  
                        <input type="hidden" id="searchbutton" name ="searchbutton" value=" "> 
                        <td width="75px" align="right">
                                <select onchange="this.form.submit()" name="dayview">
                                  <option value=""></option>
                                  <option value="today">Today</option>
                                  <option value="1dayago">1 day ago</option>
                                  <option value="2dayago">2 day ago</option>
                                  <option value="3dayago">3 day ago</option>
                                  <option value="4dayago">4 day ago</option>
                                  <option value="5dayago">5 day ago</option>
                                  <option value="6dayago">6 day ago</option>
                                  <option value="7dayago">7 day ago</option>
                        </select>
                        </td></tr></tbody></table></form></div>';
                    }
                    else{
                      echo '<div style="float:left;width:100%"><table bgcolor="#FDFDFD" width="100%" style="float:left;"> <tbody><tr>                   
                        <td width="*" align="right">'.mysql_num_rows($result).' server(s).</td></tr></tbody></table></div>';
                    }
                    echo "<br>";
                    $string_cut = array ("#");
                    $result = $dp->ExecuteQuery($strSQL);
                    while($rows = mysql_fetch_assoc($result)){
                        $serial = $rows["serial"];
                        $ip_private = $rows["ip_private"];
                        $hostname = $rows["hostname"];
                        $product = $rows["product"];
                        $osversion = str_replace($string_cut,"",explode("|", $rows["osversion"])[0]);
                        $portinfo = explode("|", $rows["portinfo"]);
                        #$updatedtime = explode(" ",$rows["updatedtime"])[0];
                        $updatedtime = $rows["updatedtime"];
                        $note = base64_decode($rows["note"]);
                        $freeram = file_size($rows["freeram"]);
                        $uptime = explode("up ",base64_decode($rows["uptime"]))[1];
                        $loadavg = load_color($rows["loadavg"]);
                        $freedisk = disk_color($rows["freedisk"]);
                        if ($note === "Not note") $note = "";
                        #$ifaces = str_replace(" 127.0.0.1;","",$rows["ifaces"]);
                        $ifaces = str_replace(", 127.0.0.1","",str_replace("127.0.0.1,","",str_replace(";",",",$rows["ifaces"])));
                        echo '<table width="85%" align="center" style="margin-top: 30px; padding-left:30px ;background-color:#BBD4ED;margin-bottom:30px">';
                        echo '<tr><td colspan="3">&nbsp</td></tr>';
                        echo '<tr><td colspan="2"><h2><center><a href="#" class="ip">'.$ip_private.'</a></center></h2></td>
                                  <td><h4 align ="center">NOTE</h4></td></tr>
                              <tr>  
                                  <td></td><td></td>
                                        <td width = "47%"rowspan = 10"> <textarea style="width:100%; resize:none" readonly rows="7" >'.$note.' </textarea></td>
                              </tr>';
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Host name</h4></td><td width = \"35%\" > $hostname</td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Product</h4></td><td width = \"35%\"> $product</td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Serial</h4></td><td width = \"35%\"> $serial</td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>OS version</h4></td><td width = \"35%\"> $osversion</td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Resouces</h4></td><td width = \"35%\"> <b>Free Ram:</b> $freeram &nbsp  <b>Free Disk:</b> $freedisk &nbsp <b>Load average: </b> $loadavg</td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Assigned IP</h4></td><td width = \"35%\"> $ifaces</td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Processes</h4></td><td width = \"35%\"> <div id='totalprocess' name='totalprocess'> 0 </div></td></tr>";
                        echo "<tr><td width = \"15%\"><h4 align = 'center'>Time</h4></td><td width = \"35%\"> Uptime: $uptime &nbsp  Updated: $updatedtime</td></tr>";
                        echo "<tr><td colspan=\"2\">&nbsp</td><td>";
                        echo "<tr><td colspan=\"2\">&nbsp</td><td>";
                        echo "</table>";
                        echo '<table width="100%" id="table_style"  style="margin-top: 20px;" ><thead class="first_row"><tr height="40px"><td>Num</td><td>User</td><td>Listen</td><td>Pid</td><td>Exe</td><td>Pwd<td width="*">Cmdline</td></tr></thead><tbody>';
                        $num = 1;
                        if ($portinfo)  {
                            foreach ($portinfo as &$line){
                                if (!empty($line)){
                                        $row = explode(",",$line);
                                        $array_sort_full[$row[2]] = $line;
                                        if (!isset($_REQUEST["sortbutton"]) && (!isset($_REQUEST["checkportbutton"]))){
                                          echo "<tr class=\"line\"><td>$num</td>";
                                          if (isset($_REQUEST["checkportbutton"])){
                                          $command = "/usr/local/nagios/libexec/check_tcp -H ".$ip_private." -p ".explode(":",$row[1])[1]." -t 2";
                                          #exec ($command , &$output , &$return_var);
                                          exec ($command,$output,$exitcode);}
                                          foreach ($row as &$item){
                                                if ($exitcode ==0){
                                                    echo "<td class=\"viewparent\"><div class='viewmore' style='height:15px; overflow:hidden;display:block; width:100%;'>$item</div></td>";}
                                                else {
                                                    echo "<td class=\"viewparent\"><div class='viewmore' style='color:red; height:15px; overflow:hidden;display:block; width:100%;'>$item</div></td>";
                                                }
                                          }
                                          echo "</tr>";
                                          $num ++;
                                        }
                                }
                            }
                            if (isset($_REQUEST["sortbutton"])){
                                ksort($array_sort_full);
                                foreach ($array_sort_full as &$item){
                                   $row1 = explode(",",$item);
                                   echo "<tr class=\"line\"><td>$num</td>";
                                   foreach ($row1 as &$item1){
                                        echo "<td class=\"viewparent\"><div class='viewmore' style='height:15px; overflow:hidden;display:block; width:100%;'>$item1</div></td>";
                                   }
                                   echo "</tr>";
                                   $num ++;
                                }
                            }
                            if (isset($_REQUEST["checkportbutton"])){
                                $command = "/usr/local/nagios/libexec/check_tcp -H ".$ip_private." -p ".explode(":",$row[1])[1]." -t 2";
                                exec ($command,$output,$exitcode);
                                echo "<tr class=\"line\"><td>$num</td>";
                                foreach ($ro as &$item){
                                    if ($exitcode != 0) {
                                         echo "<td class=\"viewparent\"><div class='viewmore' style='color:red; height:15px; overflow:hidden;display:block; width:100%;'>$item</div></td>";
                                    }
                                }
                                echo "</tr>";
                                   $num ++;
                            }
                }
                        echo "</tbody></table>";
                        echo "<br><br>";
                        $num -= 1; 
                        echo "<input type='hidden' id='totalproc' name='totalproc' value='$num'>";
                   }
                }
          }    
        }

        elseif(isset($_REQUEST["searchbutton"]) && !empty($_REQUEST["txtsearch"]) && isset($_REQUEST["fieldsearch"])  && ($_REQUEST["fieldsearch"] == 4|| $_REQUEST["fieldsearch"] == 2||$_REQUEST["fieldsearch"] == 3||$_REQUEST["fieldsearch"] == 5||$_REQUEST["fieldsearch"] == 6)){
                $string_cut = array ("release","Release","Enterprise","Microsoft","Final","#","LTS","-RELEASE","Linux","Edition","Server");
                $value = trim($_REQUEST["txtsearch"]);
                if ($_REQUEST["fieldsearch"] == 2)
                        $condition = " freeram ";
                elseif ($_REQUEST["fieldsearch"] == 3)
                        $condition = " loadavg ";
                elseif ($_REQUEST["fieldsearch"] == 4)
                        $condition = " freedisk ";
                if ($value[0] == "<")
                        $strSQL = "Select ip_private, hostname, serial, osversion, ifaces, updatedtime, product, freeram, freedisk, loadavg  From server_info Where $condition <= ".substr( $value, 1)." ORDER BY $condition DESC";
                elseif ($value[0] == ">")
                        $strSQL = "Select ip_private, hostname, serial, osversion, ifaces, updatedtime, product, freeram, freedisk, loadavg  From server_info Where $condition >= ".substr($value,1)." ORDER BY $condition DESC";
                else{
                    if ($_REQUEST["fieldsearch"] == 5){
                        $strSQL = "Select ip_private, hostname, serial, osversion, ifaces, updatedtime, product, freeram, freedisk, loadavg  From server_info Where osversion like '%".$value."%'";
                    }
                    elseif ($_REQUEST["fieldsearch"] == 6){
                        $strSQL = "Select ip_private, hostname, serial, osversion, ifaces, updatedtime, product, freeram, freedisk, loadavg  From server_info Where portinfo like '%".$value."%'";
                    }
                    else{
                        echo "<b><p>Error Format</p></b>";
                        echo "<p>Usage:  '<' or '>' value (MB)<p> ";
                        echo "<p>Example: <300   ---- To search server(s) which have below 300 MB of RAM </p>";
                        echo "<p>Usage:  '<' or '>' value <p> ";
                        echo "<p>Example: <0.10   ---- To search server(s) which have below 0.01 of Load average </p>";
                        die;
                    }
                }
                $result = $dp->ExecuteQuery($strSQL);
                if($result){
                        if(mysql_num_rows($result) == 0){
                                //$notfound  = "ERROR!!!Please Contact UP Administrator";
                                $notfound  = "Not Found";
                                echo "<h2><center>$notfound</center></h2>";
                        }else{
                                echo '<div style="float:left;width:100%"><table bgcolor="#FDFDFD" width="100%" style="float:left; height:24px"><tbody><tr height="26px"><td width="*" align="right">'.mysql_num_rows($result).' server(s).</td></tr></tbody></table></div>';
                                echo '<br>';
                                echo '<table width="100%" id="table_style"  style="margin-top: 20px;" ><thead class="first_row"><tr height="40px"><td>Num</td><td>IP</td><td>Server Name</td><td>Product</td><td>OS</td><td>Free Ram</td><td>Free Disk</td><td>LoadAvg</td><td>Assigned IP</td> </tr></thead><tbody>';
                                $num = 1;
                                while($rows = mysql_fetch_assoc($result,MYSQL_ASSOC)){
                                        echo "<tr class=\"line\">";
                                        $serial = $rows["serial"];
                                        $ip_private = $rows["ip_private"];
                                        $hostname = $rows["hostname"];
                                        $product = $rows["product"];
                                        $freeram = file_size($rows["freeram"]);
                                        $freedisk = disk_color($rows["freedisk"]);
                                        $loadavg = load_color($rows["loadavg"]);
                                        $osversion = str_replace($string_cut,"",explode("|", $rows["osversion"])[0]);
                                        //$updatedtime = explode(" ",$rows["updatedtime"])[0];
                                        $ifaces = str_replace(", 127.0.0.1","",str_replace("127.0.0.1,","",str_replace(";",",",$rows["ifaces"])));
                                        echo "<td width = \"30\">$num</td>";
                                        echo "<td width = \"100\"><a href=\"#\" class=\"ip\">$ip_private</a></td>";
                                        echo "<td width = \"200\">$hostname</td>";
                                        echo "<td width = \"50\">$product</td>";
                                        echo "<td width = \"130\">$osversion</td>";
                                        echo "<td width = \"60\">$freeram</td>";
                                        echo "<td width = \"60\">$freedisk</td>";
                                        echo "<td width = \"60\">$loadavg</td>";
                                        echo "<td width = \"*\">$ifaces</td>";
                                        //echo "<td width = \"100\"  title=".explode(" ",$rows["updatedtime"])[1].">".$updatedtime." </td>";
                                        $num ++;
                                        echo "</tr>";
                                }
                                echo "</tbody></table>";
                                echo "</br>";
                        }
                }

        }
?>

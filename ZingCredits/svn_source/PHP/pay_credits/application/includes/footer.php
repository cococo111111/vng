<script>
var newwindow;
function poptastic()
{
	 var width  = 550;
	 var height = 530;
	 var left   = (screen.width  - width)/2;
	 var top    = (screen.height - height)/2;
	 var params = 'width='+width+', height='+height;
	 params += ', top='+top+', left='+left;
	 params += ', directories=no';
	 params += ', location=no';
	 params += ', menubar=no';
	 params += ', resizable=no';
	 params += ', scrollbars=no';
	 params += ', status=no';
	 params += ', toolbar=no';

	newwindow=window.open("http://www.zing.vn/feedback/?p=me",'name',params);
	if (window.focus) {newwindow.focus()}
}
function callbackPaymentHelper(billNo,step,result){
try{
parent.callbackPayment(billNo,step,result);
}
catch(exception){}
try{
window.close();
}
catch(exception){}
}

</script>
</div>
						<div class="clear"></div>
					</div>
<?php
if(!empty($this->success) && $this->success==1){
?>
<style>
#alert {
    background: none repeat scroll 0 0 #FFF9D7;
    border-bottom: 1px solid #E2C822;
    position: relative;
    width: 100%;
    margin-left: -10px;
    margin-right: 0;
    margin-top: -10px;
    padding-right: 20px;
margin-bottom: -12px;
}
.blue {
    background: none repeat scroll 0 0 #D5FCFE !important;
    border-bottom: 1px solid #4CDDE6 !important;
}
.ctnAlert {
    padding: 5px;
    position: relative;  
}

.ctnAlert .contentAlert {
    min-height: 15px;
    position: relative;
    vertical-align: middle;
}
.zme-boxy-modal{
background:none;
}
</style>
<script type='text/javascript' src='http://static.me.zing.vn/zmjs/zm.ui-1.24.min.js'></script>
<script>
	var isboxy=false;
	function ufora(){
		var isLite=zm('#lite').attr('checked');
		var title='';
		if(isLite && !isboxy){
		isboxy=true;
		title='Bạn chọn chức năng thanh toán nhanh trong <b><?=$appInfo->appName?></b>?';			
		}
		if(!isLite && !isboxy){
		isboxy=true;
		title='Bạn không chọn chức năng thanh toán nhanh trong <b><?=$appInfo->appName?></b>?';				
		}
		if(isboxy){	
		new zmCore.Boxy({
        	title: 'Ví Zing Me - Xác nhận',
                content: title,
                modal: true,
                okButton: 'Xác nhận',
                cancelButton: 'Bỏ qua',
		onOk:function(){
	     	zm.post('<?=$payment_url?>/billing/ufora',
	     	{"appID":"<?=$appInfo->appID?>","lite":isLite?"1":"0"},
		function(data){isboxy=false;});
        	},
		onCancel:function(){zm('#lite').attr('checked',!isLite);isboxy=false;}
        	}).show();
		}
		return false;
		
	}
</script>
<div class="blue" id="alert">
    <div class="ctnAlert">
        <div class="icoEvent"></div>

        <div class="contentAlert">
<input type='checkbox' onchange='ufora()' id='lite' style='position: absolute;' <?php if(!empty($this->isLite) && $this->isLite==1){ echo 'checked=checked';} ?>> <span style='padding-left:23px;cursor:pointer' onclick='zm("#lite").click()'>Thanh toán nhanh (không cần xác nhận) cho những giao dịch sau.</span>
	</div>
	
    </div>
</div>
<?php }?>
					<!--End Mexu-->	  
	            </div>
                <div class="btmlogin">
                  <p class="bl_left"><img src="<?=$payment_url?>/images/space.gif" alt=" " width="1" height="1" /></p>
                  <p class="bl_right"><img src="<?=$payment_url?>/images/space.gif" alt=" " width="1" height="1" /></p>
                </div>
<?php if($this->actionName=='bill'):?>
<table width='100%'>
<tr>
		<td align="center"> Nếu có vấn đề xin gửi email <a href="javascript:poptastic();"><b>liên hệ</b></a> hoặc gọi vào đường dây nóng: <b>1900.561.558</b></td></tr></table>
<?php endif; ?>
              </div>
            </div>
          </div>
        </div>
        <div class="btmlogin">
          <p class="bl_left"><img src="<?=$payment_url?>/images/space.gif" alt=" " width="1" height="1" /></p>
          <p class="bl_right"><img src="<?=$payment_url?>/images/space.gif" alt=" " width="1" height="1" /></p>
        </div>
      </div>

    </div>
    <div id="footer"></div>
  </div>
  </form>
</div>
</body>
</html>

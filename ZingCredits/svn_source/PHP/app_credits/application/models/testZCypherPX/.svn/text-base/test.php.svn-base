<?php

include_once 'ZCommon.php';
include_once 'ZCypher.php';

function dumpVector($list) {
	$size = $list->size();
	echo 'Size: ' . $size . '<br/>';
	for ($i = 0; $i < $size; ++$i) {
		$item = $list->get($i);
		var_dump($item);
		echo '<br/>';
	}
}

echo 'Begin <br/>';

echo ('Module info: ' . ZCommon::zcommon_info() . '<br/>');
echo ('Module info: ' . ZCypher::zcypher_info() . '<br/>');

$params = new std__vectorT_std__string_t();

$params->push("nam.nq_0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
$params->push("stbb");
$params->push("379");
$params->push("thanhcnn2000_0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
$params->push("Chau%20Nguyen%20Nhat%20Thanh_0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
$params->push("1");
$params->push("48");
$params->push("48");
$params->push("2011-02-25%2009:43:39");

$key = new zcommon_StringHolder();
$key->value = "con kien can con ku!";

 
$data = new zcommon_StringHolder();

$e = ZCypher::zma_encode($data, $params, $key, 0, 0);
var_dump($e, $data->value);

$params2 = new std__vectorT_std__string_t();
$e = ZCypher::zma_decode($params2, $data, $key, 0, 0);
var_dump($e);
dumpVector($params2);

echo '<br/><br/>Intergration test <br/>';

//$data->value = "jBWEbTW--dxWcKlxwJ-WJFxE4jYkF-z9+iHkuvv8-qt+o6plaaJX1hcVQl673kvCzf5Y-OiusdR8dNosn33DUyIVS-oJ9jLdm9iA+P0ZioJmno26xt6I3CkPGUVVPg94sA1fbzG5t5dRgMA3p566Yzt4N3ZQIf3Srky+NuWTeD3Qh15MlMp9rllOANUVIBAq4xmyN3LKjhKZdanP1MgqVcIkUlW3PPWW5PnX+01vYIK7t4woMYAX9mpJI8-DMx5duhDBYFPH2sAIbT34hg3SaObe27cFyPB6YtHsCyhayyo0SmbjZ-JqrVbyUHwRcEkSgm9f75drvNdKTzKp";
//$data->value = "nltWjkb1WsAItANOs46TK8oB-FlD7Dz1X8EFWhfdtK-QqDoSlcNI1PM9nOEZATW1aONJmBv6f2whXTF8laokAFNUYDwgLhbwkj3xswbRotR9WD3gprQd1RtGiOVpHvz7zBxSghCcyHMqkzZmu7YXnxBUyWJdCklA-lMFRh5breBZu-jrZ1AwoecLtHljCjIfQV-PCHrzahrTsQCu8KsCCmEoqOedGg1jSe3AYYHUiMXWmEN0B5Z4VcVDxB-yBTOqq-A+gdegVJ0UsJhdOLy+PGMaRVrHLpWd0xGDz5j0H2PQwYRsNLnCSMZlN8L9E4TtUPrDoMkP017b2QWl";
$data->value = "WR4Ps6qPxZ3La5osN1dm0il71PKR6Uy7pyrvhYjkxmFBmNkY9sBnI8oMVRmoAFu2mvXri3uUoZZzb6lxSHRTDV6MPB4c0CKfzP8Tj2K5esh5pZVBMbU2GlwGLgfgGB8AxQb+sc4Zp1Vke7NEUNUMnUZDItLlR82Iu+Of4Zqxi9xlfGOR24hPcCxHFZegRgBw9hKh4O1ofliMbriKi4oaC56dRRMsGuXk89LsjRLVcMiorLd-xmInQJdQNy9uVQ4frxfSnGXs6opidSU9DO93l99ba5SKqmtha7dk7L76RE6RVf535DPh+E9nuJ00kdIni0-nCjdQudfXKSLz";
$params2 = new std__vectorT_std__string_t();
$key = new zcommon_StringHolder();
//$key->value = "con kien can con ku!123456789012";
$key->value = "con kien can con ku!";
$e = ZCypher::zma_decode($params2, $data, $key, 0, 0);
var_dump($e);
dumpVector($params2);

echo 'Exit <br/>';

?>

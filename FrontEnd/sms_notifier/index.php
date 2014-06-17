<?php

require('./twilio-php-master/Services/Twilio.php'); 
 header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST'); 
$account_sid = 'ACf724373dc116957f7070e4a8d027c645'; 
$auth_token = '85aefc6add70f7f8007bc24ddcf4f53e'; 
$client = new Services_Twilio($account_sid, $auth_token); 
 
$msg = $_GET['msg'];
$num = $_GET['num'];
$client->account->messages->create(array( 
	'To' => $num, 
	'From' => "+18143894055", 
	'Body' => $msg  
));


?>
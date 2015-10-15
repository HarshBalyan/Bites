<?php
$username ="harsh";
$password ="harsh";
$host ="localhost";
$dbname ="dinedb";

$con=mysqli_connect($host,$username,$password,$dbname);
if(!$con)
{
	echo 'connection error'.mysqli_connect_error();	
}
?>
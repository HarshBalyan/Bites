<?php
require 'init.php';

$result=mysqli_query($con,"select A.Rest_Name,B.City_Name,A.URL from rest A join places B on A.CID=B.CID;");

$response = array();
$response["restncity"] = array();
if(mysqli_num_rows($result)>0)
{
	while($row=mysqli_fetch_array($result))
	{
	$tmp=array();
	$tmp["cname"]=$row["City_Name"];
	$tmp["rname"]=$row["Rest_Name"];
	$tmp["url"]=$row["URL"];
	array_push($response["restncity"],$tmp);
	}

}
else
{
	
}
echo json_encode($response);

?>
<?php



function getCities()
{
require 'init.php';
// array for json response
    $response = array();
    $response["cities"] = array();
    $sql_query="select * from places;";
    $result=mysqli_query($con,$sql_query);
	
	while($row = mysqli_fetch_array($result))
	{
        // temporary array to create single category
        $tmp = array();
        $tmp["cid"] = $row["CID"];
        $tmp["cname"] = $row["City_Name"];
         
        // push category to final json array
        array_push($response["cities"], $tmp);
        }



// keeping response header to json
    header('Content-Type: application/json');
     
    // echoing json result
    echo json_encode($response);
}
getCities();



?>
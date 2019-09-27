


<?php
    
    include("config.php");

    $hex = $_POST['searchQuery'];
    // echo $hex;

    $busca = mysqli_query($conexao, "select * from `produto` where hex = '$hex'");
    
    $row = mysqli_fetch_array($busca);
    // $row  = "{'idProd': 1, 'nome': 'Omo'}"; 
    // $row = "aaaaa";
    // header('Content-type: application/json');
    echo json_encode($row);
    // echo "no rows";
    
//     $row = mysqli_fetch_array($busca);
// $data = $row[];

    // if($data){
    //     echo $data;
    // 

?>



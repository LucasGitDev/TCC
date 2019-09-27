
<?php	
	// $host = "sql207.epizy.com";
	//  $user = "epiz_23977633";
	//  $pass = "ta5ZLwo0NM0c40";
	//  $bd = "epiz_23977633_BancoImobiliaria";
	//  $conecao = mysqli_connect($host,$user, $pass) or die(mysqli_error());
	//  mysqli_select_db($conecao, $banco) or die(mysqli_error());
	//  if(mysqli_connect_errno($conecao)){
	//  	echo "Erro na conexao";
	//  	mysql_connect_error();
	//  }else{
	//  	echo "Ok";
	//  }

	
	$conexao = mysqli_connect("LOCALHOST","root", "","bancoSmartW");
	if(mysqli_connect_errno($conexao)){
		echo "Erro na conexao";
		mysql_connect_error();
	}else{
	// echo "Ok";
	} 
?>



<?php
  include 'connection.php';
  include 'qrcode/qrlib.php';
  $query = "SELECT * FROM tb_buku";
  $result = mysqli_query($koneksi,$query); 
  
  while ($rows = mysqli_fetch_assoc($result)) {
	  	 $data = $rows['idbarcode'];
   		   QRcode::png($data); 
		  
		  echo $data."</br>"; 
  	  } 
  
  ?>
 

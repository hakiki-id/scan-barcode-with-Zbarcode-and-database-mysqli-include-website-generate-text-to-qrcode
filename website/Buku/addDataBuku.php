<?php
  include 'connection.php';
  $barcode = $_POST['barcode'];
  $judul = $_POST['judulbuku'];
  $penerbit = $_POST['penerbitbuku'];
  $tahun = $_POST['tahunbuku'];
  $sinopsi = $_POST['sinopsisbuku']; 
 
  //if(isset($_POST['simpan'])) {
	  $filename = $_FILES['coverbuku']['name'];
		
	  $query = "Insert Into tb_buku values('$barcode','$judul','$penerbit','$tahun','$filename','$sinopsi')";
	  mysqli_query($koneksi,$query); 
	  
	  move_uploaded_file($_FILES['coverbuku']['tmp_name'], "image/".$_FILES['coverbuku']['name']);
	  
	  echo"<script>alert('Gambar Berhasil diupload !');history.go(-1);</script>"; 
	  	  
	//  }
  
  


  

 ?>

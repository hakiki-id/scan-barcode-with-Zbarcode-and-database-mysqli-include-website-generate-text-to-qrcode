<?php
  include 'connection.php';

  $getdata=$_POST['barcode'];
  $idcode = $getdata;
  $sqlscript = "SELECT * FROM `tb_buku` WHERE idbarcode LIKE '%$idcode%' ";

  $result = mysqli_query ($koneksi, $sqlscript);
  $cekbaris = mysqli_num_rows ($result);

  $tempArray = array();

  if ($cekbaris > 0) {
      while ($baris = mysqli_fetch_assoc($result)) {
            $tempArray[] = $baris;
      }
  }

  echo json_encode(array("buku"=>$tempArray));


 ?>

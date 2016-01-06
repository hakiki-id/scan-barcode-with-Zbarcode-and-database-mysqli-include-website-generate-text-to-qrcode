<?php
  include 'connection.php';

  $sqlscript = "SELECT * FROM tb_buku";

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

<!DOCTYPE html>

<?php
    include 'connection.php';
	include 'qrcode/qrlib.php';

 ?>
<html>
  <head>
      <title>DATA BUKU</title>
      <style>
   h3{
      text-align:center; }
   table {
      border-collapse:collapse;
      border-spacing:0;
      font-family:Arial, sans-serif;
      font-size:16px;
      padding-left:300px;
      margin:auto; }
   table th {
      font-weight:bold;
      padding:10px;
      color:#fff;
      background-color:#2A72BA;
      border-top:1px black solid;
      border-bottom:1px black solid;}
   table td {
      padding:10px;
      border-top:1px black solid;
      border-bottom:1px black solid;
      text-align:center; }
   tr:nth-child(even) {
     background-color: #DFEBF8; }
   </style>
  </head>
  <body>
      <h3 align="center">DATA KOLEKSI BUKU</h3>
      <br>
      <table width="1238" border="1" align="center">
        <tr>
          <th width="90">No.
          <th width="524">Judul Buku
          <th width="206">Penerbit
          <th width="107">Tahun
          <th width="147">Cover<th width="124">Barcode
        </tr>
           <?php
              $query = "SELECT * FROM tb_buku" ;
              $result = mysqli_query ($koneksi, $query);
			 
			 
			  $number =1 ;
              while ($rows = mysqli_fetch_assoc($result)) { ?>
               	 <tr>
                 <td height="102" align='center'><?php echo $number++ ?><input type='hidden' name='barcode' value='<?php echo $rows['idbarcode']?>'></td>
                 <td><?php echo $rows['judul']?></td>
                 <td><?php echo $rows['penerbit'] ?></td>
                 <td align="center"><?php echo $rows['tahun'] ?></td>
				 <td align="center"><img src=image/<?php echo$rows['image']?> width="60" height="80" /></td>
                 <td align="center">
                <a href="gen.php?barcode=<?php echo $rows['idbarcode']?>"><img alt="edit" src="icon/edit.png" /></a>
                </tr>

         <?php } ?>


       

      </table>
  </body>
</html>

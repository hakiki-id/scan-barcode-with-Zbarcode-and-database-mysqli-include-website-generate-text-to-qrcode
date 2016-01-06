<!DOCTYPE html>
<html>
  <head>
      <title>TAMBAH DATA</title>

      <script type="text/javascript">
          function PreviewImage() {
            var oFReader = new FileReader();
            oFReader.readAsDataURL (document.getElementById("uploadImage").files[0]);
            oFReader.onload = function (oFRevent) {
              document.getElementById("uploadPreview").src = oFRevent.target.result;
            };
          };
      </script>
  </head>
  <body>
  <h3 align='center'>INSERT YOUR DESCRIPTION BOOKS</h3>
	<form method="post" name="posting" action="addDataBuku.php" enctype="multipart/form-data">
    <table width="658">
      <tr>
          <td width="170" widht='400px'>Code Barcode</td>
          <td width="12">:</td>
          <td width="460"><input type="text" name="barcode" width="100"> </td>
      </tr>
      <tr>
          <td widht='400px'>Judul Buku</td>
          <td>:</td>
          <td><input name="judulbuku" type="text" size="70"> </td>
      </tr>
      <tr>
        <td widht='400px'>Penerbit</td>
        <td>:</td>
        <td><input name="penerbitbuku" type="text" size="50"></td>
      </tr>
      <tr>
        <td widht='400px'>Tahun</td>
        <td>:</td>
        <td><input name="tahunbuku" type="text" size="10"></td>
      </tr>
      <tr>
        <td widht='400px'>Sinopsis</td>
        <td>: </td>
        <td>
          <textarea name="sinopsisbuku" cols="50" rows="10" id="sinopsis"></textarea>
        </td>
      </tr>
      <tr>
        <td widht='400px'>Cover</td>
        <td>:</td>

        <td><img id="uploadPreview" style="width : 150px; height: 150px" /> </br>
            <input name="coverbuku" id="uploadImage" type=file size="50" onchange="PreviewImage();"></td>
      </tr>
      <tr>
          <td widht='400px'>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input type="submit" name="btnsimpan" value="simpan"></td>
      </tr>
    </table>
    
    </form>


  </body>
</html>

$( document ).ready( () => {
  // GET REQUEST
  $("#btnGetFiles").click( (event) => {
    event.preventDefault();
    ajaxGet();
  });
  
  // DO GET
  function ajaxGet(){
    $.ajax({
      type : "GET",
      dataType: "json",  
      url : "/api/file/all",
      success: (data) => {
        //clear old data
        $("#listFiles").html("");
        
        /*
          render list of files
        */
        $("#listFiles").append('<ul>');
        $.each(data, (index, fileUrl) => {
          var filename = fileUrl.split('\\').pop().split('/').pop();
          $("#listFiles").append('<li><a href=' + fileUrl + '>' + filename + '</a></li>');
        });
        $("#listFiles").append('</ul>');
      },
      error : (err) => {
        $("#listFiles").html(err.responseText);
      }
    });  
  }
})




$( document ).ready( () => {
  // GET REQUEST
  $("#DownloadFile").click( (event) => {
    event.preventDefault();
    ajaxDownland();
  });
 
//  Do Get file
  function ajaxDownland(){
	  $.ajax({
	  type : "GET",  
      url : "/downland/partialAbsence.xlsx",
	  success:(data) => {
		  var a = document.createElement('a');
		  var url = window.URL.createObjectURL(data);
		  a.href = url;
		  a.click();
//		  a.remove();
//		  window.URL.revokeObjectURL(url);
	  },
//	  error : (err) => {
//		console.log(err.responseText);
//	  }
	  error: (xhr, ajaxOptions, thrownError) => {
	        console.log(xhr.status);
	        console.log(ajaxOptions);
	        console.log(thrownError);
	      }
	  
	  });
  }
  
})
////  
////  // DO GET
////  function ajaxDownland(){
////    $.ajax({
////      type : "GET",
////      dataType: "json",  
////      url : "/downland/partialAbsence.xlsx",
////      success: (data) => {
//////        //clear old data
//////        $("#listFiles").html("");
//////        
//////        /*
//////          render list of files
//////        */
//////        $("#listFiles").append('<ul>');
//////        $.each(data, (index, fileUrl) => {
//////          var filename = fileUrl.split('\\').pop().split('/').pop();
//////          $("#listFiles").append('<li><a href=' + fileUrl + '>' + filename + '</a></li>');
//////        });
//////        $("#listFiles").append('</ul>');
//////      },
//////      error : (err) => {
//////        $("#listFiles").html(err.responseText);
//////      }
////    });  
////  }
////})
////





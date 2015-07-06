<!doctype html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0">
  <title>baikecpproduct</title>
  <link href="http://fonts.googleapis.com/css?family=Roboto:400,400,700" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="jsp/css/standardize.css">
  <link rel="stylesheet" href="jsp/css/baikecpproduct-grid.css">
  <link rel="stylesheet" href="jsp/css/baikecpproduct.css">
</head>

<body class="body page-baikecpproduct clearfix">
  <div class="cpproductname clearfix">
    <p class="cpproductname"><strong>${name}</strong></p>
  </div>
  <div class="actingtitle actingtitle-1 clearfix">
    <p class="actingtitle">有效成分</p>
  </div>
  <div class="actingredient actingredient-1 clearfix">
    <p class="actingredient">${activeIngredient}</p>
  </div>
  <div class="labeltitle labeltitle-1 clearfix">
    <p class="labeltitle">产品标签</p>
  </div>
  <div class="label label-1 clearfix">
    <p class="label">${name}</p>
  </div>
  <div class="formtitle formtitle-1 clearfix">
    <p class="formtitle">剂 &nbsp; &nbsp;型</p>
  </div>
  <div class="form form-1 clearfix">
    <p class="form">${type}</p>
  </div>
  <div class="distitle distitle-1 clearfix">
    <p class="distitle">防治对象<br></p>
  </div>
  <div class="disease disease-1 clearfix">
    <p class="disease">${disease}</p>
  </div>
  <div class="dostitle dostitle-1 clearfix">
    <p class="dostitle">用 &nbsp;药 &nbsp;量<br></p>
  </div>
  <div class="dosage dosage-1 clearfix">
    <p class="dosage">${volumn}</p>
  </div>
  <div class="usetitle usetitle-1 clearfix">
    <p class="usetitle">用药指导<br></p>
  </div>
  <div class="usage usage-1 clearfix">
	  	
	  	<script type="text/javascript">

		
		function myfunction(tips){
			    var head1 = tips.substring(0,1);
				i = 1;
				if(head1 == "●")
					while(tips.match("●")){
						tips = tips.replace(/●/,String(i).toString()+'.');
						i++;
					}
				else if(head1 == "１"){
					tips = tips.replace(/１/,"1");
					tips = tips.replace(/２/,"2");
					tips = tips.replace(/３/,"3");
					tips = tips.replace(/４/,"4");
					tips = tips.replace(/５/,"5");
					tips = tips.replace(/６/,"6");
				}
				
				var head2 = tips.substring(0,2);
				switch(head2){
					case '1、':
						tips = tips.replace(/(\d)、/g, "$1.");
						break;
					case '1.':
						break;
					case '[1':
						tips = tips.replace(/\[(\d)/g,"$1.");
						break;	
					case '1）':
						tips = tips.replace(/(\d)）/g,"$1.");
						break;
				}
				var tipsList = tips.replace(/(\d+\.)/g,"</p><p>$1");
			    return tipsList;
			}
		var guidelines =  "${guideline}";
		document.write("<p> " + myfunction(guidelines) );
		document.write("</p>");
		</script>
		
  </div>
  <div class="regidtitle regidtitle-1 clearfix">
    <p class="regidtitle">登 &nbsp;记 &nbsp;号<br></p>
  </div>
  <div class="regid regid-1 clearfix">
    <p class="regid">${registrationId}</p>
  </div>
  <div class="factitle factitle-1 clearfix">
    <p class="factitle">生产厂家<br></p>
  </div>
  <div class="factory factory-1 clearfix">
    <p class="factory">${manufacturer}</p>
  </div>
  <div class="tiptitle tiptitle-1 clearfix">
    <p class="tiptitle">注意事项<br></p>
  </div>
  <div class="tip tip-1 clearfix">
    <div class="tip">
    
	<script type="text/javascript">
	var tips =  "${tips}";	
	document.write("<p> " + myfunction(tips) );
	document.write("</p>");
	</script>
	
</div>
  </div>
</body>
</html>
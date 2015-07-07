<!doctype html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0">
  <title>variety</title>
  <link href="http://fonts.googleapis.com/css?family=Roboto:400,400,700" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="jsp/css/standardize.css">
  <link rel="stylesheet" href="jsp/css/variety-grid.css">
  <link rel="stylesheet" href="jsp/css/styles.css">
  <script type="text/javascript" src="jsp/js/format-baike.js"></script>
</head>
<body class="body page-variety clearfix">
  <div class="varietyname clearfix">
    <p class="varietyname"><strong>${Name}</strong></p>
  </div>
  <div class="numtitle numtitle-1 clearfix">
    <p class="numtitle">审 &nbsp;定 &nbsp;号</p>
  </div>
  <div class="number number-1 clearfix">
    <p class="number">${ExaminationNumber}</p>
  </div>
  <div class="owntitle owntitle-1 clearfix">
    <p class="owntitle">育种部门<br></p>
  </div>
  <div class="owner owner-1 clearfix">
    <p class="owner">${BreedingUnit}</p>
  </div>
  <div class="areatitle areatitle-1 clearfix">
    <p class="areatitle">适宜区域</p>
  </div>
  <div class="fitarea fitarea-1 clearfix">
    <p class="fitarea">${SuitableRegion}</p>
  </div>
  <div class="yietitle yietitle-1 clearfix">
    <p class="yietitle">产量表现<br></p>
  </div>
  <div class="yield yield-1 clearfix">
    <p class="yield">${Yield}</p>
  </div>
  <div class="chartitle clearfix">
    <p class="chatitle">品种特性<br></p>
  </div>
  <div class="characteristics characteristics-1 clearfix">
    <div class="characteristics">
        <script type="text/javascript">
		var chacts =  "${VarietyChacts}"//Characteristics
		//var chacts = "[习性](1)成虫。成虫具趋光性。雌蛾羽化后，由于无翅不能飞翔，只能沿树干爬上树。雄蛾羽化后飞到树上，白天多静止潜伏，天黑时雄蛾飞翔活跃，寻找雌蛾交尾，交尾后，当天即可产卵。(2)幼虫。幼虫孵化时，先将卵壳咬破，出壳后即可爬行吐丝。初孵幼虫食量小，随着虫龄的增长，食量猛增，暴食为害枣叶、嫩芽，甚至枣吊和枣花。";
		document.write("<p> " + formatDisaster(chacts));
		document.write("</p>");
		</script>	
</div>
  </div>
</body>
</html>
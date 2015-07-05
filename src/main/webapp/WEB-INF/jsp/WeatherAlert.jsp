<!doctype html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0">
  <title>weatheralert</title>
  <link href="http://fonts.googleapis.com/css?family=Roboto:400,400,700|Droid+Sans:700" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="jsp/css/standardize.css">
  <link rel="stylesheet" href="jsp/css/weatheralert-grid.css">
  <link rel="stylesheet" href="jsp/css/weatheralert.css">
</head>
<body class="body page-weatheralert clearfix">
  <div class="alerticon alerticon-1">
    <img class="alerticon" src="jsp/images/p0001001.png">
  </div>
  <div class="alerttitle alerttitle-1">
    <p class="alerttitle"><strong>${AlertTitle}</strong></p>
  </div>
  <div class="alertdetail alertdetail-1 clearfix">
    <p class="alertdetail">${AlertContent}
	</p>
  </div>
  <div class="timestamp timestamp-1 clearfix">
    <p class="timestamp">发布日期：&nbsp;${AlertTimeDate}&nbsp;<span>${AlertTimeClock}</span></p>
    <div class="container clearfix">
      <p class="source">来自中央气象台</p>
    </div>
  </div>
  <div class="tiphead clearfix">
    <div class="tiptitle tiptitle-1 clearfix">
      <p class="tiptitle"><strong>${AlertTitle}</strong></p>
    </div>
    <div class="levelicon levelicon-1 clearfix">
      <img class="levelicon" src="jsp/images/levels.png">
    </div>
  </div>
  <div class="tipdetail tipdetail-1 clearfix">
    <p class="tipdetail">6小时内可能或者已经受热带气旋影响，沿海或者陆地平均风力达12级以上，或者阵风达14级以上并可能持续。</p>
  </div>
</body>
</html>
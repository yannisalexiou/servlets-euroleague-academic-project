<%-- 
    Document   : view_player_stats_results
    Created on : Jan 15, 2017, 12:45:21 AM
    Author     : gioalexiou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,com.gioAlexiou.db2FirstTry.PlayerStatsForGame"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		
		<title>Databases 2 / ${PlayerNameSelected} Stats</title>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
		
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
		
		<style>
			.box
			{
			border:  1px solid gray;
			background-color:#d3d3d3;
                        }
                        .chart 
                        {
                            width: 100%; 
                            min-height: 450px;
                        }
                        .gauge
                        {
                            width: 400px;
                            height: 120px;
                        }
		</style>
                
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load('current', {'packages':['bar', 'gauge']});
                    
                    google.charts.setOnLoadCallback(drawStuff);
                    google.charts.setOnLoadCallback(drawChart);
                    google.charts.setOnLoadCallback(drawChart2);
                    google.charts.setOnLoadCallback(drawChart3);

                    function drawStuff() {
                      var data = new google.visualization.arrayToDataTable([
                        ['Stats', 'Sum'],
                        ['Total Points', ${pointsSum}],
                        ["Total Rebounds", ${reboundsStopSum}],
                        ["Blocks Fv", ${blocksFvSum}],
                        ["Mistakes", ${mistakesSum}]
                      ]);

                      var options = {
                        title: '${PlayerNameSelected} Stats',
                        width: 900,
                        legend: { position: 'none' },
                        chart: { title: '${PlayerNameSelected} Stats',
                                 subtitle: 'Game Stats' },
                        bars: 'horizontal', // Required for Material Bar Charts.
                        axes: {
                          x: {
                            0: { side: 'top', label: 'Sum'} // Top x-axis.
                          }
                        },
                        bar: { groupWidth: "90%" }
                      };

                      var chart = new google.charts.Bar(document.getElementById('top_x_div'));
                      chart.draw(data, options);
                    }
                  
                    function drawChart() {

                      var data = google.visualization.arrayToDataTable([
                        ['Label', 'Value'],
                        ['Two Points', ${twoPointsScoreSun}]
                      ]);

                      var options = {
                        width: 400, height: 120,
                        redFrom: ${twoPointsTotalSum} - 2, redTo: ${twoPointsTotalSum},
                        yellowFrom:${twoPointsTotalSum} - 5, yellowTo: ${twoPointsTotalSum} - 2,
                        minorTicks: 50
                      };

                      var chart = new google.visualization.Gauge(document.getElementById('TwoPointsGauge'));

                      chart.draw(data, options);
                    }
                    
                    function drawChart2() {

                      var data = google.visualization.arrayToDataTable([
                        ['Label', 'Value'],
                        ['Three Points', ${threePointsScoreSum}]
                      ]);

                      var options = {
                        width: 400, height: 120,
                        redFrom: ${threePointsTotalSum} - 2, redTo: ${threePointsTotalSum},
                        yellowFrom:${threePointsTotalSum} - 5, yellowTo: ${threePointsTotalSum} - 2,
                        minorTicks: 50
                      };

                      var chart = new google.visualization.Gauge(document.getElementById('ThreePointsGauge'));

                      chart.draw(data, options);
                    }
                    
                    function drawChart3() {

                      var data = google.visualization.arrayToDataTable([
                        ['Label', 'Value'],
                        ['Free Throws', ${freeThrowScoreSum}]
                      ]);

                      var options = {
                        width: 400, height: 120,
                        redFrom: ${freeThrowTotalSum} - 2, redTo: ${freeThrowTotalSum},
                        yellowFrom:${freeThrowTotalSum} - 5, yellowTo: ${freeThrowTotalSum} - 2,
                        minorTicks: 50
                      };

                      var chart = new google.visualization.Gauge(document.getElementById('FreeThrowGauge'));

                      chart.draw(data, options);
                    }
                </script>
                
                
	
	</head>
	<body>
		<!--navbar-inverse για σκουρόχρωμη navbar -->
		<div class="navbar navbar-light bg-faded">
			<div class="container">
				<div class="navbar-header">
				  
					<a href="/DB2FirstTry/index.html" class="navbar-brand mb-0">Basketball Analytics</a>
				</div>
			</div>
		</div>
		
		
		<!--Πάντα το main content σε container-->
		<div class="container">
                        <p></p>
                        <form id="GetTeamId" name="GetTeam" action="PlayerStatsResults" method="POST" enctype="multipart/form-data">
				<div class="form-group row">
				  <label for="example-search-input" class="col-md-2 col-form-label">Select Player:</label>
				  <div class="col-md-8">
					  	<select  class="form-control text-center" id="testMe" name="PlayerNameSelected">
                                                        <c:forEach var="tempPlayerName" items="${player_names_arraylist}">
                                                                <option>${tempPlayerName}</option>
                                                        </c:forEach>
                                                </select>
				  </div>
				  <div class="col-md-2">
					  <button type="submit" class="btn btn-primary">Submit</button>
				  </div>
				</div>
                        </form>
			
			<div class="row">
				<h1 class="display-3">${PlayerNameSelected} Stats</h1>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>Min</th>
				      <th>Pts</th>
				      <th>2FG</th>
				      <th>3FG</th>
				      <th>FT</th>
				      <th>Reb O</th>
				      <th>Reb D</th>
				      <th>Reb T</th>
				      <th>As</th>
				      <th>St</th>
				      <th>To</th>
				      <th>Bl Fv</th>
				      <th>Bl Ag</th>
				      <th>Fl Cm</th>
				      <th>Fl Rv</th>
				      <th>PIR</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="playerStatsItem" items="${requestScope.gameStatusArraylist}" > 
                                        <tr>
                                          <td>${playerStatsItem.minutesInGame}</td>
                                          <td>${playerStatsItem.points}</td>
                                          <td>${playerStatsItem.twoPointsP}</td>
                                          <td>${playerStatsItem.threePointsP}</td>
                                          <td>${playerStatsItem.freeThrowP}</td>
                                          <td>${playerStatsItem.reboundsOff}</td>
                                          <td>${playerStatsItem.reboundsDef}</td>
                                          <td>${playerStatsItem.reboundsTot}</td>
                                          <td>${playerStatsItem.assists}</td>
                                          <td>${playerStatsItem.steals}</td>
                                          <td>${playerStatsItem.mistakes}</td>
                                          <td>${playerStatsItem.blocksFv}</td>
                                          <td>${playerStatsItem.blocksAg}</td>
                                          <td>${playerStatsItem.foulCm}</td>
                                          <td>${playerStatsItem.foulRv}</td>
                                          <td>${playerStatsItem.pir}</td>
                                        </tr>
                                      </c:forEach>
				  </tbody>
				</table>
			</div>
                                        
            <div class="row">
                <div id="top_x_div" class="chart"></div>
                <br/>
            </div>
                        
            <div class="row">
                <div class="col-md-4"><div id="TwoPointsGauge" class="gauge"></div></div>
                <div class="col-md-4"><div id="ThreePointsGauge" class="gauge"></div></div>
                <div class="col-md-4"><div id="FreeThrowGauge" class="gauge"></div></div>
                <br/>
            </div>
            <hr/>
            <br/>
            <h2>${PlayerNameSelected} Ranking Position</h2>
            <table class="table">
            <thead>
              <tr>
                <th>Index PIR</th>
                <th>Points</th>
                <th>Rebounds</th>
                <th>Mistakes</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>${playerPirRankingPosition}</td>
                <td>${playerPointsRankingPosition}</td>
                <td>${playerReboundsRankingPosition}</td>
                <td>${playerMistakesRankingPosition}</td>
              </tr>
            </tbody>
          </table>
			
			<div class="row">
			  <ol class="breadcrumb">
			  	<li class="breadcrumb-item"><a href="/DB2FirstTry/index.html">Home</a></li>
			  	<li class="breadcrumb-item"><a href="/DB2FirstTry/MoreServlet">Analytics</a></li>
			  	<li class="breadcrumb-item active">${PlayerNameSelected} Stats</li>
			  </ol>
			</div>
		  
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	</body>
</html>
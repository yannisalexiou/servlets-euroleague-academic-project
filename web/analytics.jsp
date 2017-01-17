<%-- 
    Document   : analytics
    Created on : Jan 14, 2017, 5:30:37 PM
    Author     : gioalexiou
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>Databases 2 / Analytics</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        
	<style>
            .box
            {
                    border:  1px solid gray;
                    background-color:#d3d3d3;
            }
            
            body
            {
                background-color: #e6e6e6;
            }

            .imageScale
            {
                    height: 180px;
                    background: #ffffff;
            }
            
            .scaleZoom
            {
                object-fit: cover;
                width: 100%;
                height: 180px;
            }
	</style>

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
		<div class="row">
			<div class="col-md-4">
			<div class="card text-center">
				<img class="card-img-top scaleZoom" src="Images/teams.jpg" alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title" >Team Stats</h4>
					<form id="GetTeamId" name="GetTeam" action="TeamStatsResults" method="POST" enctype="multipart/form-data">
						<div class="form-group row">
								<select  class="form-control text-center" id="testMe" name="teamNameSelected">
									<c:forEach var="tempTeamName" items="${team_names_arraylist}">
										<option>${tempTeamName}</option>
									</c:forEach>
								</select>
						</div>
						
						<p class="card-text">See all results of your favourite team!</p>
						<input class="btn btn-primary" type="submit" value="Go">
					</form>
				</div>
			</div>
			</div>
			
			<div class="col-md-4">
			<div class="card text-center">
				<img class="card-img-top scaleZoom" src="Images/player.jpg" alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title">Player Stats</h4>
					<form id="GetPlayerStatsId" name="GetPlayerStats" action="PlayerStatsResults" method="POST" enctype="multipart/form-data">
						<div class="form-group row">
								<select  class="form-control text-center" id="testMe" name="PlayerNameSelected">
									<c:forEach var="tempPlayerName" items="${player_names_arraylist}">
										<option>${tempPlayerName}</option>
									</c:forEach>
								</select>
						</div>
						
						<p class="card-text">How good is your favourite player?</p>
						<input class="btn btn-primary" type="submit" value="Let's see">
					</form>
				</div>
			</div>
			</div>
			
			<div class="col-md-4">
			<div class="card text-center">
				<img class="card-img-top scaleZoom" src="Images/best5.jpg" alt="Card image cap">
				<div class="card-block">
					<h4 class="card-title">Best 5 Player of a Round</h4>
					<form id="GetTeamId" name="GetTeam" action="#" method="POST" enctype="multipart/form-data">
						<div class="form-group row">
								<select  class="form-control text-center" id="testMe" name="teamNameSelected">
                                                                    <option>Under Construction</option>
								</select>
						</div>
						
						<p class="card-text">See the 5 best player for specific round!</p>
						<input class="btn btn-primary" type="submit" value="Go">
					</form>
				</div>
			</div>
			<p></p>
			</div>
			
			
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="card text-center">
				<img class="card-img-top scaleZoom" src="Images/DreamTeam.jpg" alt="Card image cap">
					<div class="card-block">
					<h4 class="card-title">Dream Team</h4>
					<form id="GetTeamId" name="GetTeam" action="SelectTeamForData" method="POST" enctype="multipart/form-data">
						<p class="card-text">Best 5 Players (2 Guards, 2 Forwards and 1 Center).</p>
						<input class="btn btn-primary" type="submit" value="The best of the best are...">
					</form>
					</div>
				</div>
				<p></p>
			</div>
			
		</div>
		
		<div class="row">
			<div class="col-md-12">
                            <div class="card text-center " >
				<img class="card-img-top scaleZoom" src="Images/Finals.jpg" alt="Card image cap">
					<div class="card-block">
					<h4 class="card-title">Next Stage</h4>
					<form id="GetTeamId" name="GetTeam" action="SelectTeamForData" method="POST" enctype="multipart/form-data">
						<p class="card-text">See the results for the next 8 best teams!</p>
						<input class="btn btn-primary" type="submit" value="Go to Finals">
					</form>
					</div>
				</div>
			</div>
			
		</div>
			
		<p></p>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/DB2FirstTry/index.html">Home</a></li>
			<li class="breadcrumb-item active">Analytics</li>
		</ol>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>-->
        
</body>
</html>

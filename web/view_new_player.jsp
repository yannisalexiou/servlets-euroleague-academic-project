<%-- 
    Document   : view_new_player
    Created on : Dec 25, 2016, 9:55:59 PM
    Author     : gioalexiou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Databases 2 / View New Player</title>

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
		  <h1 class="display-3">View New Player</h1>
		  
		  <div class="alert alert-success" role="alert">
		  	<strong>Player Inserted!</strong> You successfully insert the player to DB.
		  </div>
		  
		  <div class="form-group row">
			  <label for="example-text-input" class="col-xs-2 col-form-label">ID</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${id}</p>
			  </div>
		  </div>
		  <div class="form-group row">
			  <label for="example-search-input" class="col-xs-2 col-form-label">Number</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${number}</p>
			  </div>
		  </div>
			  <div class="form-group row">
			  <label for="example-email-input" class="col-xs-2 col-form-label">Player's Name</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerName}</p>
			  </div>
		  </div>
		  <div class="form-group row">
			  <label for="example-url-input" class="col-xs-2 col-form-label">Player's Photo</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerPhoto}</p>
			  </div>
		  </div>
			  <div class="form-group row">
			  <label for="example-tel-input" class="col-xs-2 col-form-label">Player's Team</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerTeam}</p>
			  </div>
		  </div>
		  <div class="form-group row">
			  <label for="example-password-input" class="col-xs-2 col-form-label">Player's Height</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerHeight}</p>
			  </div>
		  </div>
		  <div class="form-group row">
			  <label for="example-number-input" class="col-xs-2 col-form-label">Player's Nationality</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerNationality}</p>
			  </div>
		  </div>
		  <div class="form-group row">
			  <label for="example-datetime-local-input" class="col-xs-2 col-form-label">Player's Position</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerPosition}</p>
			  </div>
		  </div>
		  <div class="form-group row">
			  <label for="example-date-input" class="col-xs-2 col-form-label">Player's Born Date</label>
			  <div class="col-xs-10">
			    <p class="form-control-static mb-0">${playerBordDate}</p>
			  </div>
		  </div>

		  
		  <div class="row">
			  <ol class="breadcrumb">
			  	<li class="breadcrumb-item"><a href="/DB2FirstTry/index.html">Home</a></li>
			  	<li class="breadcrumb-item"><a href="/DB2FirstTry/add-new-player.html">Add New Player</a></li>
			  	<li class="breadcrumb-item active">View New Player</li>
			  </ol>
		  </div>
                  
	  </div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spellbook Login</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Spellbook App</a>

                <div class="flex-fill"></div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNavAltMarkup" style="flex-grow: 0;">
                    <div class="navbar-nav">
                        <a class="nav-link" href="/">Home</a>
                        <a class="nav-link active" aria-current="page" href="/login-page">Login</a>
                        <a class="nav-link" href="#">Register</a>
                    </div>
                </div>
            </div>

        </nav>
        <div class="container-lg">
            <div class="d-flex w-100 justify-content-center">
            <div class="card mt-3">
                <h3 class="card-header">Login</h3>
                <div class="card-body p-4">
                    <form action="/login" method="POST">
	                    <label>Username</label>
	                    <input type="text" id="username" name="username" class="form-control" placeholder="username"
                            autofocus=""/>
	                    
	                    <label>Password</label>
	                    <input type="password" id="password" name="password" class="form-control" placeholder="password"/>
	                    
	                    <input type="submit" class="btn btn-primary mt-2" type="submit" value="Submit">
	                    <div style="background-color: white; color: red">${error}</div>
                    </form>
                </div>
            </div>
                
            </div>
        </div>
    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
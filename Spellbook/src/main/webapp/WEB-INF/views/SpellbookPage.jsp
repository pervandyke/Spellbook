<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	   <meta charset="UTF-8">
	   <title>Spellbook</title>
       <!-- Bootstrap Styles -->
	   <link 
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
            crossorigin="anonymous"
        />
        <!-- Custom Styles -->
        <link 
            href="/static/css/SpellbookStyles.css"
            rel="stylesheet"
        />
        <!-- AngularJS -->
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
	</head>
	
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Spellbook App</a>

                <div class="flex-fill"></div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav" style="flex-grow: 0;">
                    <div class="navbar-nav">
                        <a class="nav-link" href="/">Logout</a>
                        
                    </div>
                </div>
            </div>
        </nav>
        <div class="container-fluid ps-5 pe-5">
            <h1 class="text-center">Spellbook Page</h1>
            <h3 class="text-center mb-5">Welcome ${name}</h3>
            
            <div ng-app="SpellbookApp" ng-controller="spellController as ctrl" class="w-100">
                <div class="d-flex row">
                    <div id="formContainer" class="col-md-3">
                        <h5>Add a Spell!</h5>
                        <form id="addSpellForm">
                            <label class="form-label">Name:</label>
                            <input type="text" class="form-control" placeholder="Spell Name"/>

                            <label class="form-label">Level:</label>
                            <input type="number" min="0" max="9" class="form-control" placeholder="Spell Level (0-9)"/>

                            <label class="form-label">Damaging Spell?</label>
                            <input type="checkbox" class="form-check" ng-model="Damaging"/>

                            <div ng-if="Damaging" class="d-flex flex-column">
                                <label class="form-label">Damage Type:</label>
                                <input type="text" class="form-control" placeholder="Damage Type"/>

                                <label class="form-label">Damage Amount:</label>
                                <input type="text" class="form-control" placeholder="Damage Amount"/>
                            </div>

                            <label class="form-label">Casting Time:</label>
                            <input type="text" class="form-control" placeholder="Spell Casting Time"/>

                            <label class="form-label">Range/Area:</label>
                            <input type="text" class="form-control" placeholder="Spell Range and Area"/>

                            <label class="form-label">Components:</label>
                            <input type="text" class="form-control" placeholder="Spell Components"/>

                            <label class="form-label">Duration:</label>
                            <input type="text" class="form-control" placeholder="Spell Duration"/>
                            
                            <label class="form-label">Save Required?</label>
                            <input type="checkbox" class="form-check" ng-model="SaveRequired"/>

                            <div ng-if="SaveRequired" class="d-flex flex-column">
                                <label class="form-label">Save:</label>
                                <input type="text" class="form-control" placeholder="Save Type"/>
                            </div>

                            <label class="form-label">Description</label>
                            <textarea  class="form-control" rows="4" placeholder="Spell Description"></textarea>
                            
                            <button class="btn btn-primary mt-2">Add Spell!</button>
                        </form>
                    </div>
                    <div id="spellContainer" class="col-md-9">
                        <div id="cantrip" class="text-center">
                            <h5>Cantrip</h5>
                            <hr class="m-0 mb-1">
                            <spellListing spell="ctrl.testSpell"></spellListing>
                        </div>
                        <div id="levelOne" class="text-center">
                            <h5>1st Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelTwo" class="text-center">
                            <h5>2nd Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelThree" class="text-center">
                            <h5>3rd Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelFour" class="text-center">
                            <h5>4th Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelFive" class="text-center">
                            <h5>5th Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelSix" class="text-center">
                            <h5>6th Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelSeven" class="text-center">
                            <h5>7th Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelEight" class="text-center">
                            <h5>8th Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                        <div id="levelNine" class="text-center">
                            <h5>9th Level</h5>
                            <hr class="m-0 mb-1">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
        <!-- Bootstrap Script -->
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" 
            crossorigin="anonymous">
        </script>

        <!-- Temp AngularJS Script -->
        <script>
            const app = angular.module('SpellbookApp', []);

            app.controller('spellController', function() {
                testSpell = {
                    name: "Test Spell"
                }
            });
        </script>
        <script src="static/angularJS/components/SpellListing/spellListing.js"></script>
	</body>
</html>
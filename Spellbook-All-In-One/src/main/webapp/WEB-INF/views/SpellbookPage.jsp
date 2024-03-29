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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <!-- Custom Styles -->
        <link 
            href="/static/css/SpellbookStyles.css"
            rel="stylesheet"
        />
        <!-- AngularJS -->
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
        <script src="static/angularJS/app.js"></script>
        <script src="static/angularJS/services/spellService.js"></script>
        <script src="static/angularJS/controllers/spellController.js"></script>
        <script src="static/angularJS/controllers/spellContainerController.js"></script>
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
            <h3 class="text-center mb-5 mt-3">${name}'s Spellbook</h3>
            <h4 class="visually-hidden" id="userId">${userId}</h4>
            
            <div ng-app="SpellbookApp" ng-controller="SpellController as sc" class="w-100">
                <div class="d-flex row">
                    <div id="formContainer" class="col-md-3">
                        <h5>Add a Spell!</h5>
                        <form id="addSpellForm" name="newSpellForm">
                            <label class="form-label">Name:</label>
                            <input ng-model="sc.newSpell.name" type="text" class="form-control" placeholder="Spell Name" required/>

                            <label class="form-label">Level:</label>
                            <input ng-model="sc.newSpell.level" type="number" min="0" max="9" class="form-control" placeholder="Spell Level (0-9)" required/>

                            <label class="form-label">Damaging Spell?</label>
                            <input type="checkbox" class="form-check" ng-model="Damaging"/>

                            <div ng-if="Damaging" class="d-flex flex-column">
                                <label class="form-label">Damage Type:</label>
                                <input ng-model="sc.newSpell.damageType" type="text" class="form-control" placeholder="Damage Type"/>

                                <label class="form-label">Damage Amount:</label>
                                <input ng-model="sc.newSpell.damageAmount" type="text" class="form-control" placeholder="Damage Amount"/>
                            </div>

                            <label class="form-label">Casting Time:</label>
                            <input ng-model="sc.newSpell.castingTime" type="text" class="form-control" placeholder="Spell Casting Time" required/>

                            <label class="form-label">Range/Area:</label>
                            <input ng-model="sc.newSpell.range" type="text" class="form-control" placeholder="Spell Range and Area" required/>

                            <label class="form-label">Components:</label>
                            <input ng-model="sc.newSpell.components" type="text" class="form-control" placeholder="Spell Components"/>

                            <label class="form-label">Duration:</label>
                            <input ng-model="sc.newSpell.duration" type="text" class="form-control" placeholder="Spell Duration" required/>
                            
                            <label class="form-label">Save Required?</label>
                            <input type="checkbox" class="form-check" ng-model="SaveRequired"/>

                            <div ng-if="SaveRequired" class="d-flex flex-column">
                                <label class="form-label">Save:</label>
                                <input ng-model="sc.newSpell.save" type="text" class="form-control" placeholder="Save Type"/>
                            </div>

                            <label class="form-label">Description</label>
                            <textarea ng-model="sc.newSpell.description" class="form-control" rows="4" maxlength="600" placeholder="Spell Description"></textarea>
                            
                            <button class="btn btn-primary mt-2" ng-click="sc.createSpell()" ng-disabled="newSpellForm.$invalid">Add Spell!</button>
                        </form>
                    </div>

                    <div id="spellContainer" class="col-md-9">

                        <div class="d-flex flex-column">
                            <div class="d-flex justify-content-center align-items-center">
                                <h4 class="m-0">Spells<span ng-if="sc.editing"> (Editing)</span></h4>
                                <button class="btn" ng-click="sc.toggleEdit()"><i class="bi bi-pencil"></i></button>
                            </div>
                        </div>

                        <div id="cantrip" class="text-center">
                            <h5>Cantrip</h5>
                            <hr class="m-0 mb-1">
                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 0}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <div>
                                            <h6 class="m-0">{{spell.name}}</h6>
                                            <div class="d-flex justify-content-center">
                                                <span class="ms-2">{{spell.castingTime}}</span>
                                                <span class="ms-2">{{spell.range}}</span>
                                                <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                                <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                                <span class="ms-2">{{spell.components}}</span>
                                                <span class="ms-2">{{spell.duration}}</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelOne" class="text-center">
                            <h5>1st Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 1}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelTwo" class="text-center">
                            <h5>2nd Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 2}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelThree" class="text-center">
                            <h5>3rd Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 3}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        <div id="levelFour" class="text-center">
                            <h5>4th Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 4}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelFive" class="text-center">
                            <h5>5th Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 5}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelSix" class="text-center">
                            <h5>6th Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 6}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelSeven" class="text-center">
                            <h5>7th Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 7}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelEight" class="text-center">
                            <h5>8th Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 8}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="levelNine" class="text-center">
                            <h5>9th Level</h5>
                            <hr class="m-0 mb-1">

                            <!-- Spell Card -->
                            <div ng-repeat="spell in sc.spells | filter : {'level' : 9}" class="card mb-1">
                                <a data-bs-toggle="collapse" href="#spell{{spell.id}}">
                                    <div class="card-header noLink">
                                        <h6 class="m-0">{{spell.name}}</h6>
                                        <div class="d-flex justify-content-center">
                                            <span class="ms-2">{{spell.castingTime}}</span>
                                            <span class="ms-2">{{spell.range}}</span>
                                            <span ng-if="spell.save" class="ms-2">{{spell.save}}</span>
                                            <span ng-if="spell.damageAmount" class="ms-2">{{spell.damageAmount + " " + spell.damageType}}</span>
                                            <span class="ms-2">{{spell.components}}</span>
                                            <span class="ms-2">{{spell.duration}}</span>
                                        </div>
                                    </div>
                                </a>
                                <div class="collapse" id="spell{{spell.id}}">
                                    <div class="card-body">
                                        <p>{{spell.description}}</p>
                                        <div ng-if="sc.editing" class="d-flex justify-content-center">
                                            <button ng-click="sc.deleteSpell(spell.id)" class="btn"><i class="bi bi-x-lg"></i></button>
                                            <button ng-click="sc.editSpell(spell)" class="btn"><i class="bi bi-pencil"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>

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
	</body>
</html>
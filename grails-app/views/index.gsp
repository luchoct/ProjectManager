<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="simple"/>
		<title>Project Management</title>
		<style type="text/css" media="screen">
			#page-body {
				margin: 5em 5em 5em 5em;
        padding: 1em 1em 1em 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}
		</style>
	</head>
	<body>
		<div id="page-body" role="main">
			<p>Register the different employees that will perform as technical leads or project managers and watch all projects</p>

			<div id="controller-list" role="navigation">
				<ul>
						<li class="controller"><g:link controller="Employee">Manage the employees</g:link></li>
						<li class="controller"><g:link controller="Project">Manage the projects</g:link></li>
				</ul>
			</div>
		</div>
	</body>
</html>

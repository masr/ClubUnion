<html>
<body>
<p>
	You may type in the name of a test suite:
	<br/>
	<form action="TestServlet" method="get" name="youTypeItForm">
		<input type="text" name="suite" size=60 />
		<input type="submit" value="Run" />
	</form>
<p/>
<hr/>
<p>
	You may pick one or more of the following test suites:
	<br/>
	<form action="TestServlet" method="get" name="youPickItForm">
		<select name="suite" size="2" multiple>
			<option value="test.laliluna.tutorial.junitejb.TestBookSession">
				test.laliluna.tutorial.junitejb.TestBookSession
			</option>
		</select>
		<input type="submit" value="Run" />
	</form>
<p/>
</body>
</html>